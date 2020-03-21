package ru.sbt.mipt.oop.security.states;

import ru.sbt.mipt.oop.security.AlarmSystem;
import ru.sbt.mipt.oop.security.AlarmSystemState;
import ru.sbt.mipt.oop.security.SmartAlarmSystem;

public class DeactivatedAlarmSystem implements AlarmSystem {
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

    @Override
    public AlarmSystemState getState() {
        return AlarmSystemState.DEACTIVATED;
    }
}
