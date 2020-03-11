package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.actions.Action;

public class Door implements Actionable, HomeComponent {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void execute(Action action) {
        action.applyTo(this);
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.DOOR;
    }
}
