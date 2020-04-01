package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.components.Light;
import ru.sbt.mipt.oop.smarthome.components.SmartHome;

public class TurnOnLightCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(component -> {
            if (component instanceof Light) {
                Light light = (Light) component;
                light.setOn(true);
            }
        });
    }
}
