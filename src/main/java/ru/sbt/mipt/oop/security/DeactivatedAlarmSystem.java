package ru.sbt.mipt.oop.security;

public class DeactivatedAlarmSystem implements AlarmSystemState {
     private final SmartAlarmSystem alarmSystem;

    public DeactivatedAlarmSystem(SmartAlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void activate(String code) {
        alarmSystem.changeState(new ActivatedAlarmSystem(alarmSystem, code));
    }

    @Override
    public void deactivate(String code) {
        // do nothing
    }

    @Override
    public void alarm() {
        // do nothing
    }
}
