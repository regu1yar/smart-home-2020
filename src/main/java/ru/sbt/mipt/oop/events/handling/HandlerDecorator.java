package ru.sbt.mipt.oop.events.handling;

import ru.sbt.mipt.oop.events.types.Event;

public class HandlerDecorator implements EventHandler {
    protected final EventHandler wrappee;

    public HandlerDecorator(EventHandler wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void handleEvent(Event event) {
        wrappee.handleEvent(event);
    }
}
