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
                turnOnLights(event.getObjectId());
                break;
            case LIGHT_OFF:
                turnOffLights(event.getObjectId());
                break;
        }
    }

    private void turnOnLights(String objectId) {
        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;

                room.execute(roomComponent -> {
                    if (roomComponent instanceof Light) {
                        Light light = (Light) roomComponent;

                        if (light.getId().equals(objectId)) {
                            light.setOn(true);
                            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                        }

                    }
                });

            }
        });
    }

    private void turnOffLights(String objectId) {
        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;

                room.execute(roomComponent -> {
                    if (roomComponent instanceof Light) {
                        Light light = (Light) roomComponent;

                        if (light.getId().equals(objectId)) {
                            light.setOn(false);
                            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                        }

                    }
                });

            }
        });
    }
}
