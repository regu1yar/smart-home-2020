package ru.sbt.mipt.oop.events.producers;

import ru.sbt.mipt.oop.events.types.SensorEvent;
import ru.sbt.mipt.oop.events.types.EventType;

public class RandomEventProducer implements EventProducer {
    @Override
    public SensorEvent getNextSensorEvent() {
        if (Math.random() < 0.05) return null; // null means end of event stream
        EventType sensorEventType = EventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
