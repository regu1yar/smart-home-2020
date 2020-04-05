package ru.sbt.mipt.oop.applications.application;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.smarthome.commands.CommandSender;
import ru.sbt.mipt.oop.smarthome.components.SmartHome;
import ru.sbt.mipt.oop.smarthome.events.handling.EventHandlerAdapter;
import ru.sbt.mipt.oop.smarthome.events.handling.decorators.SecurityDecorator;
import ru.sbt.mipt.oop.smarthome.events.handling.handlers.*;
import ru.sbt.mipt.oop.smarthome.notifications.StubSmsNotifier;
import ru.sbt.mipt.oop.smarthome.security.AlarmSystem;
import ru.sbt.mipt.oop.smarthome.security.SmartAlarmSystem;
import ru.sbt.mipt.oop.smarthome.serialization.SmartHomeDeserializer;
import ru.sbt.mipt.oop.smarthome.serialization.SmartHomeJsonDeserializer;

import java.util.Map;

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
    EventHandler doorEventHandler() {
        return new EventHandlerAdapter(new SecurityDecorator(new DoorEventHandler(smartHome()), alarmSystem()));
    }

    @Bean
    EventHandler lightEventHandler() {
        return new EventHandlerAdapter(new SecurityDecorator(new LightEventHandler(smartHome()), alarmSystem()));
    }

    @Bean
    EventHandler closeHallDoorEventHandler() {
        return new EventHandlerAdapter(new SecurityDecorator(
                new CloseHallDoorEventHandler(smartHome(), new CommandSender(smartHome())),
                alarmSystem()
        ));
    }

    @Bean
    EventHandler alarmNotificationHandler() {
        return new EventHandlerAdapter(new AlarmNotificationHandler(alarmSystem(), new StubSmsNotifier()));
    }

    @Bean
    EventHandler alarmEventHandler() {
        return new EventHandlerAdapter(new AlarmEventHandler(alarmSystem()));
    }

    @Bean
    @Autowired
    SensorEventsManager eventsManager(Map<String, EventHandler> handlers) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        for (EventHandler handler : handlers.values()) {
            sensorEventsManager.registerEventHandler(handler);
        }

        return sensorEventsManager;
    }
}
