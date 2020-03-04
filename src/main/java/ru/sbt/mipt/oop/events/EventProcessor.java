package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.events.handlers.EventHandler;

import java.util.Collection;


public class EventProcessor {
    private Collection<EventHandler> handlers;

    public EventProcessor(Collection<EventHandler> handlers) {
        this.handlers = handlers;
    }

    public void processEvent(SensorEvent event) {
        for (EventHandler handler : handlers) {
            handler.handleEvent(event);
        }
    }
}
