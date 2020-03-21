package ru.sbt.mipt.oop.events.producers;

import ru.sbt.mipt.oop.events.types.SensorEvent;

public interface EventProducer {
    SensorEvent getNextSensorEvent();
}
