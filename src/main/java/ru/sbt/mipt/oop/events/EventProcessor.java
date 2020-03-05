package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.events.producers.EventProducer;

import java.util.Collection;


public class EventProcessor {
    private Collection<EventHandler> handlers;
    private EventProducer eventProducer;

    public EventProcessor(Collection<EventHandler> handlers, EventProducer eventProducer) {
        this.handlers = handlers;
        this.eventProducer = eventProducer;
    }

    public void startProcessing() {
        SensorEvent event = eventProducer.getNextSensorEvent();
        while (event != null) {
            processEvent(event);
            event = eventProducer.getNextSensorEvent();
        }
    }

    private void processEvent(SensorEvent event) {
        for (EventHandler handler : handlers) {
            handler.handleEvent(event);
        }
    }
}
