package ru.sbt.mipt.oop.security;

public interface AlarmSystem {
    void activate(String code);
    void deactivate(String code);
    void alarm();
    AlarmSystemState getState();
}
