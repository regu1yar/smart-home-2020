package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.EventProcessor;
import ru.sbt.mipt.oop.events.SensorEventsManagerAdaptor;
import ru.sbt.mipt.oop.events.handling.decorators.SecurityDecorator;
import ru.sbt.mipt.oop.events.handling.handlers.*;
import ru.sbt.mipt.oop.notifications.StubSmsNotifier;
import ru.sbt.mipt.oop.security.AlarmSystem;
import ru.sbt.mipt.oop.security.SmartAlarmSystem;
import ru.sbt.mipt.oop.serialization.SmartHomeDeserializer;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonDeserializer;

public class Application {

    public static void main(String... args) {
        SmartHomeDeserializer deserializer = new SmartHomeJsonDeserializer("output.js");
        SmartHome smartHome = deserializer.deserialize();

        AlarmSystem alarmSystem = new SmartAlarmSystem();

        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        EventProcessor eventProcessor = new SensorEventsManagerAdaptor(sensorEventsManager);
        eventProcessor.registerHandler(new SecurityDecorator(new DoorEventHandler(smartHome), alarmSystem));
        eventProcessor.registerHandler(new SecurityDecorator(new LightEventHandler(smartHome), alarmSystem));
        eventProcessor.registerHandler(new SecurityDecorator(
                        new CloseHallDoorEventHandler(smartHome, new CommandSender(smartHome)),
                        alarmSystem
        ));
        eventProcessor.registerHandler(new AlarmNotificationHandler(alarmSystem, new StubSmsNotifier()));
        eventProcessor.registerHandler(new AlarmEventHandler(alarmSystem));

        eventProcessor.startProcessing();
    }

}
