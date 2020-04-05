package ru.sbt.mipt.oop.smarthome.remotecontrol.factories;

import ru.sbt.mipt.oop.smarthome.components.SmartHome;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.TurnOnLightCommand;

public class TurnOnLightCommandFactory implements CommandFactory {
    private final SmartHome smartHome;

    public TurnOnLightCommandFactory(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public Command createCommand() {
        return new TurnOnLightCommand(smartHome);
    }
}
