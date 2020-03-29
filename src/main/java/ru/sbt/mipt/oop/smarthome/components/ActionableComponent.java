package ru.sbt.mipt.oop.smarthome.components;

import ru.sbt.mipt.oop.smarthome.actions.Action;

public interface ActionableComponent {
    void execute(Action action);
}
