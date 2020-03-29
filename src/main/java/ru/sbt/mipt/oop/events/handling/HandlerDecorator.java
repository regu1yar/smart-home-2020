package ru.sbt.mipt.oop.events.handling;

import ru.sbt.mipt.oop.events.types.Event;

public interface HandlerDecorator extends EventHandler {
    void handleEvent(Event event);
}
