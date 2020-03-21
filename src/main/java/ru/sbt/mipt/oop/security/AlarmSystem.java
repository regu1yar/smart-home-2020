package ru.sbt.mipt.oop.security;

public interface AlarmSystem {
    void activate(String password);
    void deactivate(String password);
    void alarm();
    AlarmSystemState getState();
}
