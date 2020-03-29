package ru.sbt.mipt.oop.smarthome.commands;

import ru.sbt.mipt.oop.smarthome.components.SmartHome;

public class CommandSender {
    private final SmartHome smartHome;

    public CommandSender(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
