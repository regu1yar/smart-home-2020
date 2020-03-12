package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.actions.Action;

import static ru.sbt.mipt.oop.components.HomeComponentType.DOOR;

public class Door implements HomeComponent, Actionable {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public HomeComponentType getComponentType() {
        return DOOR;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void execute(Action action) {
        action.applyTo(this);
    }
}
