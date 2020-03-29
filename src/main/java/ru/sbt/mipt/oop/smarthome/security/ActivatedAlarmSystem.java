package ru.sbt.mipt.oop.smarthome.security;

public class ActivatedAlarmSystem implements AlarmSystemState {
    private final SmartAlarmSystem alarmSystem;
    private final String code;

    public ActivatedAlarmSystem(SmartAlarmSystem alarmSystem, String code) {
        this.alarmSystem = alarmSystem;
        this.code = code;
    }

    @Override
    public void activate(String code) {
        // do nothing
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(this.code)) {
            alarmSystem.changeState(new DeactivatedAlarmSystem(alarmSystem));
        } else {
            alarmSystem.changeState(new AlarmingAlarmSystem(alarmSystem, code));
        }
    }

    @Override
    public void alarm() {
        alarmSystem.changeState(new AlarmingAlarmSystem(alarmSystem, code));
    }
}
