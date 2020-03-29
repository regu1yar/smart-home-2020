package ru.sbt.mipt.oop.smarthome.actions;

import ru.sbt.mipt.oop.smarthome.components.ActionableComponent;

public interface Action {
    void applyTo(ActionableComponent component);
}
