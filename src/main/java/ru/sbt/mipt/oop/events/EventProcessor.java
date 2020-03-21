package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.events.types.Event;
import ru.sbt.mipt.oop.events.handling.EventHandler;
import ru.sbt.mipt.oop.events.producing.EventProducer;

import java.util.Collection;


public class EventProcessor {
    private Collection<EventHandler> handlers;
    private EventProducer eventProducer;

    public EventProcessor(Collection<EventHandler> handlers, EventProducer eventProducer) {
        this.handlers = handlers;
        this.eventProducer = eventProducer;
    }

    public void startProcessing() {
        Event event = eventProducer.getNextEvent();
        while (event != null) {
            processEvent(event);
            event = eventProducer.getNextEvent();
        }
    }

    private void processEvent(Event event) {
        for (EventHandler handler : handlers) {
            handler.handleEvent(event);
        }
    }
}
