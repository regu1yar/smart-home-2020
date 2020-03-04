package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonSerializer;
import ru.sbt.mipt.oop.serialization.SmartHomeSerializer;

import java.io.IOException;
import java.util.Arrays;

public class HomeBuilder {

    public static void main(String[] args) throws IOException {
        Room kitchen = new Room(Arrays.asList(
                new Light("1", false), new Light("2", true),
                new Door(false, "1")
        ), "kitchen");
        Room bathroom = new Room(Arrays.asList(
                new Light("3", true),
                new Door(false, "2")
        ), "bathroom");
        Room bedroom = new Room(Arrays.asList(
                new Light("4", false), new Light("5", false), new Light("6", false),
                new Door(true, "3")
        ), "bedroom");
        Room hall = new Room(Arrays.asList(
                new Light("7", false), new Light("8", false), new Light("9", false),
                new Door(false, "4")
        ), "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));

        SmartHomeSerializer serializer = new SmartHomeJsonSerializer();
        serializer.serialize(smartHome, "output.js");
    }

}
