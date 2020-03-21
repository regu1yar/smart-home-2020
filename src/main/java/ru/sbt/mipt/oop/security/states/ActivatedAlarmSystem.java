package ru.sbt.mipt.oop.security.states;

import ru.sbt.mipt.oop.security.AlarmSystem;
import ru.sbt.mipt.oop.security.AlarmSystemState;
import ru.sbt.mipt.oop.security.SmartAlarmSystem;

public class ActivatedAlarmSystem implements AlarmSystem {
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
