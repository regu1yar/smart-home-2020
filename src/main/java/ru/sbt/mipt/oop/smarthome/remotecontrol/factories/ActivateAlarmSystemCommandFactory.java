package ru.sbt.mipt.oop.smarthome.remotecontrol.factories;

import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.ActivateAlarmSystemCommand;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;
import ru.sbt.mipt.oop.smarthome.security.AlarmSystem;

public class ActivateAlarmSystemCommandFactory implements CommandFactory {
    private final AlarmSystem alarmSystem;

    public ActivateAlarmSystemCommandFactory(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public Command createCommand() {
        return new ActivateAlarmSystemCommand(alarmSystem);
    }
}
