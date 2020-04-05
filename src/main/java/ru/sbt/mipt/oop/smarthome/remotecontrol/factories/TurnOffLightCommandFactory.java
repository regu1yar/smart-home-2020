package ru.sbt.mipt.oop.smarthome.remotecontrol.factories;

import ru.sbt.mipt.oop.smarthome.components.SmartHome;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.TurnOffLightCommand;

public class TurnOffLightCommandFactory implements CommandFactory {
    private final SmartHome smartHome;

    public TurnOffLightCommandFactory(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public Command createCommand() {
        return new TurnOffLightCommand(smartHome);
    }
}
