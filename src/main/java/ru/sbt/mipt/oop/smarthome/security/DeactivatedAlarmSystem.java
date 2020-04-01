package ru.sbt.mipt.oop.smarthome.security;

public class DeactivatedAlarmSystem implements AlarmSystemState {
    private final SmartAlarmSystem alarmSystem;
    private final String defaultCode;

    public DeactivatedAlarmSystem(SmartAlarmSystem alarmSystem, String defaultCode) {
        this.alarmSystem = alarmSystem;
        this.defaultCode = defaultCode;
    }

    @Override
    public void activate(String code) {
        alarmSystem.changeState(new ActivatedAlarmSystem(alarmSystem, code, defaultCode));
    }

    @Override
    public void activate() {
        alarmSystem.changeState(new ActivatedAlarmSystem(alarmSystem, defaultCode, defaultCode));
    }

    @Override
    public void deactivate(String code) {
        // do nothing
    }

    @Override
    public void deactivate() {
        // do nothing
    }

    @Override
    public void alarm(String code) {
        alarmSystem.changeState(new AlarmingAlarmSystem(alarmSystem, code, defaultCode));
    }

    @Override
    public void alarm() {
        alarmSystem.changeState(new AlarmingAlarmSystem(alarmSystem, defaultCode, defaultCode));
    }
}
