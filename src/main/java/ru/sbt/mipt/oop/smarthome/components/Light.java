package ru.sbt.mipt.oop.smarthome.components;

import ru.sbt.mipt.oop.smarthome.actions.Action;

public class Light implements ActionableComponent {
    private final String id;
    private boolean isOn;

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

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void execute(Action action) {
        action.applyTo(this);
    }
}
