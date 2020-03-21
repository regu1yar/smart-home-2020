package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.events.types.Event;

public interface EventHandler {
    void handleEvent(Event event);
}
