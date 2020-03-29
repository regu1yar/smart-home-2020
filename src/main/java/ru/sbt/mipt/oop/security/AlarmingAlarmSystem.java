package ru.sbt.mipt.oop.security;

public class AlarmingAlarmSystem implements AlarmSystemState {
    private final SmartAlarmSystem alarmSystem;
    private final String code;

    public AlarmingAlarmSystem(SmartAlarmSystem alarmSystem, String code) {
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
        }
    }

    @Override
    public void alarm() {
        // do nothing
    }
}
