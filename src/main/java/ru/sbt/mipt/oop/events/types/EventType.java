package ru.sbt.mipt.oop.events.types;

import java.util.EnumSet;

public enum EventType {
    LIGHT_ON, LIGHT_OFF, DOOR_OPEN, DOOR_CLOSED, ALARM_ACTIVATE, ALARM_DEACTIVATE;

    public static EnumSet<EventType> getSensorEvents() {
        return EnumSet.of(LIGHT_OFF, LIGHT_ON, DOOR_CLOSED, DOOR_OPEN);
    }

    public static EnumSet<EventType> getAlarmSystemEvents() {
        return EnumSet.of(ALARM_ACTIVATE, ALARM_DEACTIVATE);
    }
}
