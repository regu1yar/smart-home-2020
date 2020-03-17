package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.components.SmartHome;

public class CommandSender {
    private final SmartHome smartHome;

    public CommandSender(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
