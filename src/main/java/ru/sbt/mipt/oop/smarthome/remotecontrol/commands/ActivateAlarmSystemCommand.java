package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.security.AlarmSystem;

public class ActivateAlarmSystemCommand implements Command {
    private final AlarmSystem alarmSystem;

    public ActivateAlarmSystemCommand(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void execute() {
        alarmSystem.activate();
    }
}
