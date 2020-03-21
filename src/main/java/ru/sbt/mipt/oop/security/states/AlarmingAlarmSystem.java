package ru.sbt.mipt.oop.security.states;

import ru.sbt.mipt.oop.security.AlarmSystem;
import ru.sbt.mipt.oop.security.AlarmSystemState;
import ru.sbt.mipt.oop.security.SmartAlarmSystem;

public class AlarmingAlarmSystem implements AlarmSystem {
    private final SmartAlarmSystem alarmSystem;

    public AlarmingAlarmSystem(SmartAlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void activate(String password) {
        // do nothing
    }

    @Override
    public void deactivate(String password) {
        // do nothing
    }

    @Override
    public void alarm() {
        // do nothing
    }

    @Override
    public AlarmSystemState getState() {
        return AlarmSystemState.ALARMING;
    }
}
