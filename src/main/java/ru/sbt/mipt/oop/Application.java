package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.EventProcessor;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.LightEventHandler;
import ru.sbt.mipt.oop.events.producers.EventProducer;
import ru.sbt.mipt.oop.events.producers.RandomEventProducer;
import ru.sbt.mipt.oop.serialization.SmartHomeDeserializer;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonDeserializer;

import java.io.IOException;
import java.util.Arrays;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHomeDeserializer deserializer = new SmartHomeJsonDeserializer("output.js");
        SmartHome smartHome = deserializer.deserialize();

        EventProcessor eventProcessor = new EventProcessor(Arrays.asList(
                new DoorEventHandler(smartHome),
                new LightEventHandler(smartHome)
        ));

        EventProducer eventProducer = new RandomEventProducer();
        SensorEvent event = eventProducer.getNextSensorEvent();
        while (event != null) {
            eventProcessor.processEvent(event);
            event = eventProducer.getNextSensorEvent();
        }
    }
}
