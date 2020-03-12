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
                openDoors(event.getObjectId());
                break;
            case DOOR_CLOSED:
                closeDoors(event.getObjectId());
                break;
        }
    }

    private void openDoors(String objectId) {
        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;

                room.execute(roomComponent -> {
                    if (roomComponent instanceof Door) {
                        Door door = (Door) roomComponent;

                        if (door.getId().equals(objectId)) {
                            door.setOpen(true);
                            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                        }

                    }
                });

            }
        });
    }

    private void closeDoors(String objectId) {
        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;

                room.execute(roomComponent -> {
                    if (roomComponent instanceof Door) {
                        Door door = (Door) roomComponent;

                        if (door.getId().equals(objectId)) {
                            door.setOpen(false);
                            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                        }

                    }
                });

            }
        });
    }
}
