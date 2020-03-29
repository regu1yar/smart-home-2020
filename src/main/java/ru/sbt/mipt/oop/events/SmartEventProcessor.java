package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.events.types.Event;
import ru.sbt.mipt.oop.events.handling.EventHandler;
import ru.sbt.mipt.oop.events.producing.EventProducer;

import java.util.Collection;


public class SmartEventProcessor implements EventProcessor {
    private Collection<EventHandler> handlers;
    private EventProducer eventProducer;

    public SmartEventProcessor(Collection<EventHandler> handlers, EventProducer eventProducer) {
        this.handlers = handlers;
        this.eventProducer = eventProducer;
    }

    @Override
    public void startProcessing() {
        Event event = eventProducer.getNextEvent();
        while (event != null) {
            processEvent(event);
            event = eventProducer.getNextEvent();
        }
    }

    @Override
    public void registerHandler(EventHandler handler) {
        handlers.add(handler);
    }

    private void processEvent(Event event) {
        for (EventHandler handler : handlers) {
            handler.handleEvent(event);
        }
    }
}
