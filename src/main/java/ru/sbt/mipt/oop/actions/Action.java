package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.components.ActionableComponent;

public interface Action {
    void applyTo(ActionableComponent component);
}
