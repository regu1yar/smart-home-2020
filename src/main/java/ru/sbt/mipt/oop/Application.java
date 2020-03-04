package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.EventProcessor;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.events.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.events.handlers.LightEventHandler;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonSerializer;
import ru.sbt.mipt.oop.serialization.SmartHomeSerializer;

import java.io.IOException;
import java.util.Arrays;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHomeSerializer serializer = new SmartHomeJsonSerializer();
        SmartHome smartHome = serializer.deserialize("output.js");

        EventProcessor eventProcessor = new EventProcessor(Arrays.asList(
                new DoorEventHandler(smartHome),
                new LightEventHandler(smartHome)
        ));
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            eventProcessor.processEvent(event);
            event = getNextSensorEvent();
        }
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
