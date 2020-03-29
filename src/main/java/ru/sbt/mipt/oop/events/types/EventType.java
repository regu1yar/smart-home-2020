package ru.sbt.mipt.oop.events.types;

import java.util.EnumSet;

public enum EventType {
    LIGHT_ON, LIGHT_OFF,
    DOOR_OPEN, DOOR_CLOSED, DOOR_LOCKED, DOOR_UNLOCKED,
    ALARM_ACTIVATE, ALARM_DEACTIVATE;

    public static EnumSet<EventType> getLightEvents() {
        return EnumSet.of(LIGHT_OFF, LIGHT_ON);
    }

    public static EnumSet<EventType> getDoorEvents() {
        return EnumSet.of(DOOR_CLOSED, DOOR_OPEN, DOOR_LOCKED, DOOR_UNLOCKED);
    }

    public static EnumSet<EventType> getAlarmSystemEvents() {
        return EnumSet.of(ALARM_ACTIVATE, ALARM_DEACTIVATE);
    }
}
