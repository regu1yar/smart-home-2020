package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.EventProcessor;
import ru.sbt.mipt.oop.events.handlers.CloseHallDoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.LightEventHandler;
import ru.sbt.mipt.oop.events.producers.EventProducer;
import ru.sbt.mipt.oop.events.producers.RandomEventProducer;
import ru.sbt.mipt.oop.serialization.SmartHomeDeserializer;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonDeserializer;

import java.util.Arrays;

public class Application {

    public static void main(String... args) {
        SmartHomeDeserializer deserializer = new SmartHomeJsonDeserializer("output.js");
        SmartHome smartHome = deserializer.deserialize();

        EventProducer eventProducer = new RandomEventProducer();
        EventProcessor eventProcessor = new EventProcessor(Arrays.asList(
                new DoorEventHandler(smartHome),
                new LightEventHandler(smartHome),
                new CloseHallDoorEventHandler(smartHome)
        ), eventProducer);

        eventProcessor.startProcessing();
    }

}
