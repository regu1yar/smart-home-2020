package ru.sbt.mipt.oop.applications.homebuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.smarthome.components.Door;
import ru.sbt.mipt.oop.smarthome.components.Light;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.components.SmartHome;
import ru.sbt.mipt.oop.smarthome.serialization.SmartHomeJsonSerializer;
import ru.sbt.mipt.oop.smarthome.serialization.SmartHomeSerializer;

import java.util.Arrays;

@Configuration
public class HomeBuilderConfiguration {
    @Bean
    SmartHome smartHome() {
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
        return new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
    }

    @Bean
    SmartHomeSerializer smartHomeSerializer() {
        return new SmartHomeJsonSerializer("output.js");
    }
}
