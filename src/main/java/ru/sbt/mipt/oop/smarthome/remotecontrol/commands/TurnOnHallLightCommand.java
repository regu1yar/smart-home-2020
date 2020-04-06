package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.components.Light;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.components.SmartHome;

public class TurnOnHallLightCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnHallLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(component -> {
            if (!(component instanceof Room)) return;
            Room room = (Room) component;
            if (!room.getName().equals("hall")) return;
            room.execute(roomComponent -> {
                if (!(roomComponent instanceof Light)) return;
                Light ligtj = (Light) roomComponent;
                ligtj.setOn(true);
            });
        });
    }
}
