package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.actions.Action;

import java.util.Collection;

public class Room implements ActionableComponent {
    private Collection<ActionableComponent> smartObjectComponents;
    private String name;

    public Room(Collection<ActionableComponent> smartObjectComponents, String name) {
        this.smartObjectComponents = smartObjectComponents;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.applyTo(this);

        for (ActionableComponent smartObjectComponent : smartObjectComponents) {
            smartObjectComponent.execute(action);
        }
    }
}
