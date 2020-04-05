package ru.sbt.mipt.oop.smarthome.remotecontrol.factories;

import ru.sbt.mipt.oop.smarthome.components.SmartHome;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.TurnOnHallLightCommand;

public class TurnOnHallLightCommandFactory implements CommandFactory {
    private final SmartHome smartHome;

    public TurnOnHallLightCommandFactory(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public Command createCommand() {
        return new TurnOnHallLightCommand(smartHome);
    }
}
