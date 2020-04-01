package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.security.AlarmSystem;

public class AlarmCommand implements Command {
    private final AlarmSystem alarmSystem;

    public AlarmCommand(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void execute() {
        alarmSystem.alarm();
    }
}
