package ru.sbt.mipt.oop.smarthome.security;

public class ActivatedAlarmSystem implements AlarmSystemState {
    private final SmartAlarmSystem alarmSystem;
    private final String code;
    private final String defaultCode;

    public ActivatedAlarmSystem(SmartAlarmSystem alarmSystem, String code, String defaultCode) {
        this.alarmSystem = alarmSystem;
        this.code = code;
        this.defaultCode = defaultCode;
    }

    @Override
    public void activate(String code) {
        // do nothing
    }

    @Override
    public void activate() {
        // do nothing
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(this.code)) {
            alarmSystem.changeState(new DeactivatedAlarmSystem(alarmSystem, defaultCode));
        } else {
            alarmSystem.changeState(new AlarmingAlarmSystem(alarmSystem, this.code, defaultCode));
        }
    }

    @Override
    public void deactivate() {
        alarmSystem.changeState(new DeactivatedAlarmSystem(alarmSystem, defaultCode));
    }

    @Override
    public void alarm(String code) {
        alarm();
    }

    @Override
    public void alarm() {
        alarmSystem.changeState(new AlarmingAlarmSystem(alarmSystem, code, defaultCode));
    }
}
