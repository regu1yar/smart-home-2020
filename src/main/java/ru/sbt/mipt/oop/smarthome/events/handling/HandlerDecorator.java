package ru.sbt.mipt.oop.smarthome.events.handling;

import ru.sbt.mipt.oop.smarthome.events.types.Event;

public interface HandlerDecorator extends EventHandler {
    void handleEvent(Event event);
}
