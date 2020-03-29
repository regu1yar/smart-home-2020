package ru.sbt.mipt.oop.smarthome.events.handling;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.smarthome.events.types.Event;
import ru.sbt.mipt.oop.smarthome.events.types.EventType;
import ru.sbt.mipt.oop.smarthome.events.types.SensorEvent;

import java.util.Map;

import static ru.sbt.mipt.oop.smarthome.events.types.EventType.*;

public class EventHandlerAdapter implements com.coolcompany.smarthome.events.EventHandler {
    private final EventHandler adapteeHandler;

    private static final Map<String, EventType> ccEventTypesToEventTypes = Map.of(
            "LightIsOn", LIGHT_ON,
            "LightIsOff", LIGHT_OFF,
            "DoorIsOpen", DOOR_OPEN,
            "DoorIsClosed", DOOR_CLOSED,
            "DoorIsLocked", DOOR_LOCKED,
            "DoorIsUnlocked", DOOR_UNLOCKED
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
        return new SensorEvent(ccEventTypesToEventTypes.get(event.getEventType()), event.getObjectId());
    }
}
