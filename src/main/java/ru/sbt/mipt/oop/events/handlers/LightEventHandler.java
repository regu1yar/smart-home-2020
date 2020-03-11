package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.components.ComponentType;
import ru.sbt.mipt.oop.components.HomeComponent;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
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
        smartHome.execute(new Action() {
            @Override
            public void applyTo(HomeComponent component) {
                if (component.getComponentType() == ComponentType.LIGHT && component.getId().equals(objectId)) {

                }
            }
        });
        for (Room room : smartHome.getRooms()) {
            Light light = room.getLightById(objectId);
            if (light != null) {
                light.setOn(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            }
        }
    }

    private void turnOffLights(String objectId) {
        for (Room room : smartHome.getRooms()) {
            Light light = room.getLightById(objectId);
            if (light != null) {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }
}
