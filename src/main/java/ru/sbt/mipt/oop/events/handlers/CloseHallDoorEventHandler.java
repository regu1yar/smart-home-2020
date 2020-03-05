package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartObject;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.objects.SmartObjectType.DOOR;
import static ru.sbt.mipt.oop.objects.SmartObjectType.LIGHT;

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
            for (SmartObject smartObject : homeRoom.getAllSmartObjectsByType(LIGHT)) {
                Light light = (Light) smartObject;
                light.turnOff();
            }
        }
    }
}
