package ru.sbt.mipt.oop.smarthome.events.factories;

import ru.sbt.mipt.oop.smarthome.events.types.Event;
import ru.sbt.mipt.oop.smarthome.events.types.EventType;
import ru.sbt.mipt.oop.smarthome.events.types.SensorEvent;

public class DoorLockedEventFactory implements SensorEventFactory {
    @Override
    public Event createEvent(String objectId) {
        return new SensorEvent(EventType.DOOR_LOCKED, objectId);
    }
}
