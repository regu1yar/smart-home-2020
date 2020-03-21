package ru.sbt.mipt.oop.security.states;

import ru.sbt.mipt.oop.security.AlarmSystem;
import ru.sbt.mipt.oop.security.AlarmSystemState;
import ru.sbt.mipt.oop.security.SmartAlarmSystem;

public class ActivatedAlarmSystem implements AlarmSystem {
    private final SmartAlarmSystem alarmSystem;
    private final String password;

    public ActivatedAlarmSystem(SmartAlarmSystem alarmSystem, String password) {
        this.alarmSystem = alarmSystem;
        this.password = password;
    }

    @Override
    public void activate(String password) {
        // do nothing
    }

    @Override
    public void deactivate(String password) {
        if (password.equals(this.password)) {
            alarmSystem.changeState(new DeactivatedAlarmSystem(alarmSystem));
        } else {
            alarmSystem.changeState(new AlarmingAlarmSystem(alarmSystem));
        }
    }

    @Override
    public void alarm() {
        alarmSystem.changeState(new AlarmingAlarmSystem(alarmSystem));
    }

    @Override
    public AlarmSystemState getState() {
        return AlarmSystemState.ACTIVATED;
    }
}
