package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.HomeComponent;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.components.HomeComponentType.DOOR;
import static ru.sbt.mipt.oop.components.HomeComponentType.LIGHT;

public class CloseHallDoorEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public CloseHallDoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() != DOOR_CLOSED) {
            return;
        }

        checkDoorAndTurnOffLightIfNeeded(event.getObjectId());
    }

    private void checkDoorAndTurnOffLightIfNeeded(String objectId) {
        for (Room room : smartHome.getRooms()) {
            Door door = (Door) room.getSmartObjectByIdAndType(objectId, DOOR);
            if (door != null) {
                if (room.getName().equals("hall")) {
                    turnOffAllLights();
                }
            }
        }
    }

    private void turnOffAllLights() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (HomeComponent homeComponent : homeRoom.getAllSmartObjectsByType(LIGHT)) {
                Light light = (Light) homeComponent;
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
