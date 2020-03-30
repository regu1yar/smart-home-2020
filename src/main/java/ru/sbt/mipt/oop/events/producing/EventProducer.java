package ru.sbt.mipt.oop.events.producing;

import ru.sbt.mipt.oop.events.types.Event;

public interface EventProducer {
    Event getNextEvent();
}
