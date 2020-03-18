package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonSerializer;
import ru.sbt.mipt.oop.serialization.SmartHomeSerializer;

import java.util.Arrays;

public class HomeBuilder {

    public static void main(String[] args) {
        Room kitchen = new Room(Arrays.asList(
                new Light("1", false), new Light("2", true),
                new Door("1", false)
        ), "kitchen");
        Room bathroom = new Room(Arrays.asList(
                new Light("3", true),
                new Door("2", false)
        ), "bathroom");
        Room bedroom = new Room(Arrays.asList(
                new Light("4", false), new Light("5", false), new Light("6", false),
                new Door("3", true)
        ), "bedroom");
        Room hall = new Room(Arrays.asList(
                new Light("7", false), new Light("8", false), new Light("9", false),
                new Door("4", false)
        ), "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));

        SmartHomeSerializer serializer = new SmartHomeJsonSerializer("output.js");
        serializer.serialize(smartHome);
    }

}
