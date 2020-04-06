package ru.sbt.mipt.oop.smarthome.remotecontrol.factories;

import ru.sbt.mipt.oop.smarthome.components.SmartHome;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.CloseHallDoorCommand;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;

public class CloseHallDoorCommandFactory implements CommandFactory {
    private final SmartHome smartHome;

    public CloseHallDoorCommandFactory(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public Command createCommand() {
        return new CloseHallDoorCommand(smartHome);
    }
}
