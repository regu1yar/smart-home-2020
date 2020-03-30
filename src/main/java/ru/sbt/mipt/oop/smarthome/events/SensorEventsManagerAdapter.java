package ru.sbt.mipt.oop.smarthome.events;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.smarthome.events.handling.EventHandler;
import ru.sbt.mipt.oop.smarthome.events.handling.EventHandlerAdapter;

public class SensorEventsManagerAdapter implements EventProcessor {
    private final SensorEventsManager adapteeEventsManager;

    public SensorEventsManagerAdapter(SensorEventsManager eventsManager) {
        this.adapteeEventsManager = eventsManager;
    }

    @Override
    public void startProcessing() {
        adapteeEventsManager.start();
    }

    @Override
    public void registerHandler(EventHandler handler) {
        com.coolcompany.smarthome.events.EventHandler ccSensorEventHandler = convertHandler(handler);
        adapteeEventsManager.registerEventHandler(ccSensorEventHandler);
    }

    private com.coolcompany.smarthome.events.EventHandler convertHandler(EventHandler handler) {
        return new EventHandlerAdapter(handler);
    }
}
