package ru.sbt.mipt.oop.smarthome.remotecontrol.factories;

import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;

public interface CommandFactory {
    Command createCommand();
}
