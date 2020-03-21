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
    public void activate(String password) {
        alarmSystem.changeState(new ActivatedAlarmSystem(alarmSystem, password));
    }

    @Override
    public void deactivate(String password) {
        // do nothing
    }

    @Override
    public void alarm() {
        alarmSystem.changeState(new AlarmingAlarmSystem(alarmSystem));
    }

    @Override
    public AlarmSystemState getState() {
        return AlarmSystemState.DEACTIVATED;
    }
}
