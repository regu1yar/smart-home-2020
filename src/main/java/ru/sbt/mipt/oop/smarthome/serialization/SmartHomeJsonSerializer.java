package ru.sbt.mipt.oop.smarthome.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.smarthome.components.ActionableComponent;
import ru.sbt.mipt.oop.smarthome.components.SmartHome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SmartHomeJsonSerializer implements SmartHomeSerializer {
    Gson gson;
    String target;

    public SmartHomeJsonSerializer(String target) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(
                ActionableComponent.class,
                new ActionableComponentInterfaceAdaptor()
        );

        this.gson = gsonBuilder.setPrettyPrinting().create();
        this.target = target;
    }

    @Override
    public void serialize(SmartHome smartHome) {
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get(target);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        } catch (IOException e) {
            throw new RuntimeException("Problem with writing json to " + target);
        }
    }
}
