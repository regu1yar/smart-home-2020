package ru.sbt.mipt.oop.smarthome.events.handling;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.smarthome.events.factories.*;
import ru.sbt.mipt.oop.smarthome.events.types.Event;
import ru.sbt.mipt.oop.smarthome.exceptions.UnknownEventTypeException;

import java.util.Map;

public class EventHandlerAdapter implements com.coolcompany.smarthome.events.EventHandler {
    private final EventHandler adapteeHandler;

    private static final Map<String, SensorEventFactory> ccEventTypesToEventTypes = Map.of(
            "LightIsOn", new LightOnEventFactory(),
            "LightIsOff", new LightOffEventFactory(),
            "DoorIsOpen", new DoorOpenEventFactory(),
            "DoorIsClosed", new DoorClosedEventFactory(),
            "DoorIsLocked", new DoorLockedEventFactory(),
            "DoorIsUnlocked", new DoorUnlockedEventFactory()
    );

    public EventHandlerAdapter(EventHandler adapteeHandler) {
        this.adapteeHandler = adapteeHandler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        Event sensorEvent = null;
        try {
            sensorEvent = convertEvent(event);
        } catch (UnknownEventTypeException e) {
            e.printStackTrace();
        }
        adapteeHandler.handleEvent(sensorEvent);
    }

    private Event convertEvent(CCSensorEvent event) throws UnknownEventTypeException {
        if (!ccEventTypesToEventTypes.containsKey(event.getEventType())) {
            throw new UnknownEventTypeException("Unknown CCSensorEvent type: " + event.getEventType());
        }
        return ccEventTypesToEventTypes.get(event.getEventType()).createEvent(event.getObjectId());
    }
}
