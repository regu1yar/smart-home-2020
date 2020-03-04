package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.objects.Light;

import static ru.sbt.mipt.oop.objects.SmartObjectType.LIGHT;

public class LightEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        switch (event.getType()) {
            case LIGHT_ON:
                turnOnLights(event.getObjectId());
                break;
            case LIGHT_OFF:
                turnOffLights(event.getObjectId());
                break;
        }
    }

    private void turnOffLights(String objectId) {
        for (Room room : smartHome.getRooms()) {
            Light light = (Light) room.getSmartObjectByIdAndType(objectId, LIGHT);
            if (light != null) {
                light.setOn(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            }
        }
    }

    private void turnOnLights(String objectId) {
        for (Room room : smartHome.getRooms()) {
            Light light = (Light) room.getSmartObjectByIdAndType(objectId, LIGHT);
            if (light != null) {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }
}
