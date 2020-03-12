package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.components.Door;

import static ru.sbt.mipt.oop.components.HomeComponentType.DOOR;
import static ru.sbt.mipt.oop.components.HomeComponentType.LIGHT;

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
        smartHome.execute(component -> {
            if (component.getComponentType() == DOOR && component.getId().equals(objectId)) {
                Door door = (Door) component;
                door.setOpen(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
            }
        });
    }

    private void openDoors(String objectId) {
        smartHome.execute(component -> {
            if (component.getComponentType() == DOOR && component.getId().equals(objectId)) {
                Door door = (Door) component;
                door.setOpen(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
            }
        });
    }
}
