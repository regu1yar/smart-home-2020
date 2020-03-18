package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.actions.Action;

public class Door implements ActionableComponent {
    private final String id;
    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public Door(String id, boolean isOpen) {
        this.id = id;
        this.isOpen = isOpen;
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
}
