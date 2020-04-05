package ru.sbt.mipt.oop.smarthome.events.handling;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.smarthome.events.factories.*;
import ru.sbt.mipt.oop.smarthome.events.types.Event;
import ru.sbt.mipt.oop.smarthome.events.types.EventType;
import ru.sbt.mipt.oop.smarthome.events.types.SensorEvent;

import java.util.Map;

import static ru.sbt.mipt.oop.smarthome.events.types.EventType.*;

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
        Event sensorEvent = convertEvent(event);
        adapteeHandler.handleEvent(sensorEvent);
    }

    private Event convertEvent(CCSensorEvent event) {
        return ccEventTypesToEventTypes.get(event.getEventType()).createEvent(event.getObjectId());
    }
}
