package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.components.*;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.SensorEvent;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;

public class CloseHallDoorEventHandler implements EventHandler {
    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public CloseHallDoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
        commandSender = new CommandSender(smartHome);
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() != DOOR_CLOSED) {
            return;
        }

        checkDoorAndTurnOffLightIfNeeded(event.getObjectId());
    }

    private void checkDoorAndTurnOffLightIfNeeded(String objectId) {
        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;

                if (room.getName().equals("hall")) {
                    room.execute(roomComponent -> {
                        if (roomComponent instanceof Door) {
                            Door door = (Door) roomComponent;

                            if (door.getId().equals(objectId)) {
                                turnOffAllLights();
                            }

                        }
                    });
                }

            }
        });
    }

    private void turnOffAllLights() {
        smartHome.execute(component -> {
            if (component instanceof Light) {
                Light light = (Light) component;
                light.setOn(false);

                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                commandSender.sendCommand(command);
            }
        });
    }
}
