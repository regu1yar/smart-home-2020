package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;

import java.util.Collection;

public class Room implements Actionable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    private Light getLightById(String id) {
        for (Light light : lights) {
            if (light.getId().equals(id)) {
                return light;
            }
        }

        return null;
    }

    private Door getDoorById(String id) {
        for (Door door : doors) {
            if (door.getId().equals(id)) {
                return door;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        for (Light light : lights) {
            light.execute(action);
        }

        for (Door door : doors) {
            door.execute(action);
        }
    }
}
