package ru.sbt.mipt.oop.smarthome.events;

import ru.sbt.mipt.oop.smarthome.events.handling.EventHandler;

public interface EventProcessor {
    void startProcessing();
    void registerHandler(EventHandler handler);
}
