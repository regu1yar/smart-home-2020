package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.objects.SmartObject;
import ru.sbt.mipt.oop.objects.SmartObjectType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Room {
    private Map<SmartObjectType, Collection<SmartObject>> smartObjects;
    private String name;

    public Room(Collection<SmartObject> smartObjects, String name) {
        this.smartObjects = new HashMap<>();
        for (SmartObject smartObject : smartObjects) {
            if (!this.smartObjects.containsKey(smartObject.getObjectType())) {
                this.smartObjects.put(smartObject.getObjectType(), new ArrayList<>());
            }

            this.smartObjects.get(smartObject.getObjectType()).add(smartObject);
        }

        this.name = name;
    }

    public SmartObject getSmartObjectByIdAndType(String id, SmartObjectType objectType) {
        if (!smartObjects.containsKey(objectType)) {
            return null;
        }

        for (SmartObject smartObject : smartObjects.get(objectType)) {
            if (smartObject.getId().equals(id)) {
                return smartObject;
            }
        }

        return null;
    }

    public Collection<SmartObject> getAllSmartObjectsByType(SmartObjectType objectType) {
        return smartObjects.get(objectType);
    }

    public String getName() {
        return name;
    }
}
