package ru.sbt.mipt.oop.security;

import ru.sbt.mipt.oop.security.states.DeactivatedAlarmSystem;

public class SmartAlarmSystem implements AlarmSystem {
    private AlarmSystem state = new DeactivatedAlarmSystem(this);

    @Override
    public void activate(String code) {
        state.activate(code);
    }

    @Override
    public void deactivate(String code) {
        state.deactivate(code);
    }

    @Override
    public void alarm() {
        state.alarm();
    }

    @Override
    public AlarmSystemState getState() {
        return state.getState();
    }

    public void changeState(AlarmSystem newState) {
        state = newState;
    }
}
