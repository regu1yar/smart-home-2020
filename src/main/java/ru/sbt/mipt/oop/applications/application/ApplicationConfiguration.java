package ru.sbt.mipt.oop.applications.application;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.smarthome.commands.CommandSender;
import ru.sbt.mipt.oop.smarthome.components.SmartHome;
import ru.sbt.mipt.oop.smarthome.events.EventProcessor;
import ru.sbt.mipt.oop.smarthome.events.SensorEventsManagerAdapter;
import ru.sbt.mipt.oop.smarthome.events.handling.decorators.SecurityDecorator;
import ru.sbt.mipt.oop.smarthome.events.handling.handlers.*;
import ru.sbt.mipt.oop.smarthome.notifications.StubSmsNotifier;
import ru.sbt.mipt.oop.smarthome.security.AlarmSystem;
import ru.sbt.mipt.oop.smarthome.security.SmartAlarmSystem;
import ru.sbt.mipt.oop.smarthome.serialization.SmartHomeDeserializer;
import ru.sbt.mipt.oop.smarthome.serialization.SmartHomeJsonDeserializer;

@Configuration
public class ApplicationConfiguration {
    @Bean
    SmartHome smartHome() {
        SmartHomeDeserializer deserializer = new SmartHomeJsonDeserializer("output.js");
        return deserializer.deserialize();
    }

    @Bean
    AlarmSystem alarmSystem() {
        return new SmartAlarmSystem();
    }

    @Bean
    EventProcessor eventProcessor() {
        SmartHome smartHome = smartHome();
        AlarmSystem alarmSystem = alarmSystem();

        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        EventProcessor eventProcessor = new SensorEventsManagerAdapter(sensorEventsManager);
        eventProcessor.registerHandler(new SecurityDecorator(new DoorEventHandler(smartHome), alarmSystem));
        eventProcessor.registerHandler(new SecurityDecorator(new LightEventHandler(smartHome), alarmSystem));
        eventProcessor.registerHandler(new SecurityDecorator(
                new CloseHallDoorEventHandler(smartHome, new CommandSender(smartHome)),
                alarmSystem
        ));
        eventProcessor.registerHandler(new AlarmNotificationHandler(alarmSystem, new StubSmsNotifier()));
        eventProcessor.registerHandler(new AlarmEventHandler(alarmSystem));

        return eventProcessor;
    }
}
