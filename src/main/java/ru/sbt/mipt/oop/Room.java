package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;

import java.util.Collection;

public class Room {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Light getLightById(String id) {
        for (Light light : lights) {
            if (light.getId().equals(id)) {
                return light;
            }
        }

        return null;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public Door getDoorById(String id) {
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
}
