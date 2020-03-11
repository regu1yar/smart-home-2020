package ru.sbt.mipt.oop.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeJsonDeserializer implements SmartHomeDeserializer {
    Gson gson;
    String source;

    public SmartHomeJsonDeserializer(String source) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        this.gson = gsonBuilder.setPrettyPrinting().create();
        this.source = source;
    }

    @Override
    public SmartHome deserialize() {
        String json;
        try {
            json = new String(Files.readAllBytes(Paths.get(source)));
        } catch (IOException e) {
            throw new RuntimeException("Problem with reading json from " + source);
        }
        return gson.fromJson(json, SmartHome.class);
    }
}
