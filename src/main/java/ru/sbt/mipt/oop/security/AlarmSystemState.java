package ru.sbt.mipt.oop.security;

public interface AlarmSystemState {
    void activate(String code);
    void deactivate(String code);
    void alarm();
}
