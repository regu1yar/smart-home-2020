package ru.sbt.mipt.oop.smarthome.security;

public interface AlarmSystem {
    void activate(String code);
    void deactivate(String code);
    void alarm();
    AlarmSystemState getState();
}
