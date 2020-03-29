package ru.sbt.mipt.oop.smarthome.events.producing;

import ru.sbt.mipt.oop.smarthome.events.types.Event;

public interface EventProducer {
    Event getNextEvent();
}
