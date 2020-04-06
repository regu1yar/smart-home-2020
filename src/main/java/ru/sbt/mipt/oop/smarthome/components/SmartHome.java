package ru.sbt.mipt.oop.smarthome.components;

import ru.sbt.mipt.oop.smarthome.actions.Action;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements ActionableComponent {
    private Collection<ActionableComponent> homeComponents;

    public SmartHome() {
        homeComponents = new ArrayList<>();
    }

    public SmartHome(Collection<ActionableComponent> homeComponents) {
        this.homeComponents = homeComponents;
    }

    @Override
    public void execute(Action action) {
        action.applyTo(this);

        for (ActionableComponent component : homeComponents) {
            component.execute(action);
        }
    }
}
