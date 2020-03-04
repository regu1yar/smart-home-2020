package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface EventHandler {
    public void handleEvent(SensorEvent event);
}
