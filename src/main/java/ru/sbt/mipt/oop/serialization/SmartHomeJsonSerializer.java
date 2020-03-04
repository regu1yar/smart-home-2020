package ru.sbt.mipt.oop.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.objects.SmartObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SmartHomeJsonSerializer implements SmartHomeSerializer {
    Gson gson;

    public SmartHomeJsonSerializer() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(
                SmartObject.class,
                new SmartObjectInterfaceAdaptor<>()
        );

        this.gson = gsonBuilder.setPrettyPrinting().create();
    }

    @Override
    public void serialize(SmartHome smartHome, String target) throws IOException {
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get(target);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }

    @Override
    public SmartHome deserialize(String source) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(source)));
        return gson.fromJson(json, SmartHome.class);
    }
}
