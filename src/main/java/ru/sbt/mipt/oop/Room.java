package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.components.HomeComponent;
import ru.sbt.mipt.oop.components.HomeComponentType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Room implements Actionable {
    private Map<HomeComponentType, Collection<HomeComponent>> homeComponents;
    private String name;

    public Room(Collection<HomeComponent> homeComponents, String name) {
        this.homeComponents = new HashMap<>();
        for (HomeComponent homeComponent : homeComponents) {
            if (!this.homeComponents.containsKey(homeComponent.getComponentType())) {
                this.homeComponents.put(homeComponent.getComponentType(), new ArrayList<>());
            }

            this.homeComponents.get(homeComponent.getComponentType()).add(homeComponent);
        }

        this.name = name;
    }

    private HomeComponent getSmartObjectByIdAndType(String id, HomeComponentType objectType) {
        if (!homeComponents.containsKey(objectType)) {
            return null;
        }

        for (HomeComponent homeComponent : homeComponents.get(objectType)) {
            if (homeComponent.getId().equals(id)) {
                return homeComponent;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        for (Collection<HomeComponent> components : homeComponents.values()) {
            for (HomeComponent component : components) {
                action.applyTo(component);
            }
        }
    }
}
