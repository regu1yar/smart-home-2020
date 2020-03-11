package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.Door;

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
            Door door= room.getDoorById(objectId);
            if (door != null) {
                door.setOpen(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
            }
        }
    }

    private void openDoors(String objectId) {
        for (Room room : smartHome.getRooms()) {
            Door door = room.getDoorById(objectId);
            if (door != null) {
                door.setOpen(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
            }
        }
    }
}
