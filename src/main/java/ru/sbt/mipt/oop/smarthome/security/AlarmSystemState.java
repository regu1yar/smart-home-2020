package ru.sbt.mipt.oop.smarthome.security;

public interface AlarmSystemState {
    void activate(String code);
    void deactivate(String code);
    void alarm();
}
