package ru.sbt.mipt.oop.smarthome.events.handling;

import ru.sbt.mipt.oop.smarthome.events.types.Event;

public interface EventHandler {
    void handleEvent(Event event);
}
