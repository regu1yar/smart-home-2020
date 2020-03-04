package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;

import static ru.sbt.mipt.oop.objects.SmartObjectType.LIGHT;

public class Light implements SmartObject {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    @Override
    public SmartObjectType getObjectType() {
        return LIGHT;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void turnOff() {
        setOn(false);
        SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, id);
        sendCommand(command);
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}