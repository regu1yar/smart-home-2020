package ru.sbt.mipt.oop.security;

public class SmartAlarmSystem implements AlarmSystem {
    private AlarmSystemState state = new DeactivatedAlarmSystem(this);

    @Override
    public void activate(String code) {
        state.activate(code);
    }

    @Override
    public void deactivate(String code) {
        state.deactivate(code);
    }

    @Override
    public void alarm() {
        state.alarm();
    }

    @Override
    public AlarmSystemState getState() {
        return state;
    }

    void changeState(AlarmSystemState newState) {
        state = newState;
    }
}
