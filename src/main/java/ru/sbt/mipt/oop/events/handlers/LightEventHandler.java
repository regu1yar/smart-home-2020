package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.Light;

public class LightEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        switch (event.getType()) {
            case LIGHT_ON:
                toggleLight(event.getObjectId(), true);
                break;
            case LIGHT_OFF:
                toggleLight(event.getObjectId(), false);
                break;
        }
    }

    private void toggleLight(String objectId, boolean isOn) {
        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;

                room.execute(roomComponent -> {
                    if (roomComponent instanceof Light) {
                        Light light = (Light) roomComponent;

                        if (light.getId().equals(objectId)) {
                            light.setOn(isOn);
                            if (isOn) {
                                logAction(light, room, "was turned on");
                            } else {
                                logAction(light, room, "was turned off");
                            }
                        }

                    }
                });

            }
        });
    }

    private static void logAction(Light light, Room room, String action) {
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " " + action);
    }
}
