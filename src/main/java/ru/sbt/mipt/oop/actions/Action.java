package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.components.HomeComponent;

public interface Action {
    void applyTo(HomeComponent component);
}
