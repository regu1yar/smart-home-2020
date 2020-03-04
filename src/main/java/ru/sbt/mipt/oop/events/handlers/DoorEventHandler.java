package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartObject;

import static ru.sbt.mipt.oop.objects.SmartObjectType.DOOR;
import static ru.sbt.mipt.oop.objects.SmartObjectType.LIGHT;

public class DoorEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        switch (event.getType()) {
            case DOOR_OPEN:
                openDoors(event.getObjectId());
                break;
            case DOOR_CLOSED:
                closeDoors(event.getObjectId());
                break;
        }
    }

    private void closeDoors(String objectId) {
        for (Room room : smartHome.getRooms()) {
            Door door= (Door)room.getSmartObjectByIdAndType(objectId, DOOR);
            if (door != null) {
                door.setOpen(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                if (room.getName().equals("hall")) {
                    turnOffAllLights();
                }
            }
        }
    }

    private void openDoors(String objectId) {
        for (Room room : smartHome.getRooms()) {
            Door door = (Door)room.getSmartObjectByIdAndType(objectId, DOOR);
            if (door != null) {
                door.setOpen(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
            }
        }
    }

    private void turnOffAllLights() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (SmartObject smartObject : homeRoom.getAllSmartObjectsByType(LIGHT)) {
                Light light = (Light) smartObject;
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
