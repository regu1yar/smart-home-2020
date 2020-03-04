package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartObject;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonSerializer;
import ru.sbt.mipt.oop.serialization.SmartHomeSerializer;
import ru.sbt.mipt.oop.serialization.SmartObjectInterfaceAdaptor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
