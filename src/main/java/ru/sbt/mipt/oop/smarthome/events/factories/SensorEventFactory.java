package ru.sbt.mipt.oop.smarthome.events.factories;

import ru.sbt.mipt.oop.smarthome.events.types.Event;

public interface SensorEventFactory {
    Event createEvent(String objectId);
}
