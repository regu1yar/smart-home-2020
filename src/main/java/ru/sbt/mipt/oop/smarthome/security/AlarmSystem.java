package ru.sbt.mipt.oop.smarthome.security;

public interface AlarmSystem {
    void activate(String code);
    void activate();
    void deactivate(String code);
    void deactivate();
    void alarm(String code);
    void alarm();
    AlarmSystemState getState();
}
