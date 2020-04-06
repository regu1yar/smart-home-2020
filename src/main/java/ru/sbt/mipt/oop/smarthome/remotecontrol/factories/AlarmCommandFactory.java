package ru.sbt.mipt.oop.smarthome.remotecontrol.factories;

import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.AlarmCommand;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;
import ru.sbt.mipt.oop.smarthome.security.AlarmSystem;

public class AlarmCommandFactory implements CommandFactory {
    private final AlarmSystem alarmSystem;

    public AlarmCommandFactory(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public Command createCommand() {
        return new AlarmCommand(alarmSystem);
    }
}
