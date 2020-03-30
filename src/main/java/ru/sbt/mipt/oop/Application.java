package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.EventProcessor;
import ru.sbt.mipt.oop.events.handling.decorators.SecurityDecorator;
import ru.sbt.mipt.oop.events.handling.handlers.*;
import ru.sbt.mipt.oop.events.producing.EventProducer;
import ru.sbt.mipt.oop.events.producing.RandomEventProducer;
import ru.sbt.mipt.oop.notifications.StubSmsNotifier;
import ru.sbt.mipt.oop.security.AlarmSystem;
import ru.sbt.mipt.oop.security.SmartAlarmSystem;
import ru.sbt.mipt.oop.serialization.SmartHomeDeserializer;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonDeserializer;

import java.util.Arrays;

public class Application {

    public static void main(String... args) {
        SmartHomeDeserializer deserializer = new SmartHomeJsonDeserializer("output.js");
        SmartHome smartHome = deserializer.deserialize();

        AlarmSystem alarmSystem = new SmartAlarmSystem();

        EventProducer eventProducer = new RandomEventProducer();
        EventProcessor eventProcessor = new EventProcessor(Arrays.asList(
                new SecurityDecorator(new DoorEventHandler(smartHome), alarmSystem),
                new SecurityDecorator(new LightEventHandler(smartHome), alarmSystem),
                new SecurityDecorator(
                        new CloseHallDoorEventHandler(smartHome, new CommandSender(smartHome)),
                        alarmSystem
                ),
                new AlarmNotificationHandler(alarmSystem, new StubSmsNotifier()),
                new AlarmEventHandler(alarmSystem)
        ), eventProducer);

        eventProcessor.startProcessing();
    }

}
