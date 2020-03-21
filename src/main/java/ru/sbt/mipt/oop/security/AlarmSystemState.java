package ru.sbt.mipt.oop.security;

import java.util.EnumSet;

public enum AlarmSystemState {
    ACTIVATED, DEACTIVATED, ALARMING;

    public static EnumSet<AlarmSystemState> getSafeStates() {
        return EnumSet.of(DEACTIVATED);
    }

    public static EnumSet<AlarmSystemState> getUnsafeStates() {
        return EnumSet.of(ACTIVATED, ALARMING);
    }
}
