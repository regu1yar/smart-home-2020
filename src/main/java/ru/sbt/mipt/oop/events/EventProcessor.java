package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.events.handling.EventHandler;

public interface EventProcessor {
    void startProcessing();
    void registerHandler(EventHandler handler);
}
