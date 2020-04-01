package ru.sbt.mipt.oop.smarthome.security;

public class SmartAlarmSystem implements AlarmSystem {
    private AlarmSystemState state;

    private static final String DEFAULT_CODE = "DEFAULT_CODE";

    public SmartAlarmSystem() {
        this.state = new DeactivatedAlarmSystem(this, DEFAULT_CODE);
    }

    public SmartAlarmSystem(String defaultCode) {
        state = new DeactivatedAlarmSystem(this, defaultCode);
    }

    @Override
    public void activate(String code) {
        state.activate(code);
    }

    @Override
    public void activate() {
        state.activate();
    }

    @Override
    public void deactivate(String code) {
        state.deactivate(code);
    }

    @Override
    public void deactivate() {
        state.deactivate();
    }

    @Override
    public void alarm(String code) {
        state.alarm(code);
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
