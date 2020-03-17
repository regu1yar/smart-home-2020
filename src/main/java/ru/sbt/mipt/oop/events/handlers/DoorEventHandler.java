package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.components.*;
import ru.sbt.mipt.oop.events.SensorEvent;

public class DoorEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        switch (event.getType()) {
            case DOOR_OPEN:
                toggleDoors(event.getObjectId(), true);
                break;
            case DOOR_CLOSED:
                toggleDoors(event.getObjectId(), false);
                break;
        }
    }

    private void toggleDoors(String objectId, boolean isOpen) {
        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;

                room.execute(roomComponent -> {
                    if (roomComponent instanceof Door) {
                        Door door = (Door) roomComponent;

                        if (door.getId().equals(objectId)) {
                            door.setOpen(isOpen);
                            if (isOpen) {
                                logAction(door, room, "was opened");
                            } else {
                                logAction(door, room, "was closed");
                            }
                        }

                    }
                });

            }
        });
    }

    private static void logAction(Door door, Room room, String action) {
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " " + action);
    }
}
