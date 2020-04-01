package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.components.Door;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.components.SmartHome;

public class CloseHallDoorCommand implements Command {
    private final SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(component -> {
            if (!(component instanceof Room)) return;
            Room room = (Room) component;
            if (!room.getName().equals("hall")) return;
            room.execute(roomComponent -> {
                if (!(roomComponent instanceof Door)) return;
                Door door = (Door) roomComponent;
                door.setOpen(false);
            });
        });
    }
}
