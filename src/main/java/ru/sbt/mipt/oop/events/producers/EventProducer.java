package ru.sbt.mipt.oop.events.producers;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface EventProducer {
    public SensorEvent getNextSensorEvent();
}
