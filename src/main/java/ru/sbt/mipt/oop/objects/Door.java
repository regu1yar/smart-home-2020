package ru.sbt.mipt.oop.objects;

import static ru.sbt.mipt.oop.objects.SmartObjectType.DOOR;

public class Door implements SmartObject {
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
    public SmartObjectType getObjectType() {
        return DOOR;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
