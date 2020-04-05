package ru.sbt.mipt.oop.applications.application;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.smarthome.commands.CommandSender;
import ru.sbt.mipt.oop.smarthome.components.SmartHome;
import ru.sbt.mipt.oop.smarthome.events.handling.EventHandlerAdapter;
import ru.sbt.mipt.oop.smarthome.events.handling.decorators.SecurityDecorator;
import ru.sbt.mipt.oop.smarthome.events.handling.handlers.*;
import ru.sbt.mipt.oop.smarthome.notifications.StubSmsNotifier;
import ru.sbt.mipt.oop.smarthome.remotecontrol.RemoteController;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.*;
import ru.sbt.mipt.oop.smarthome.security.AlarmSystem;
import ru.sbt.mipt.oop.smarthome.security.SmartAlarmSystem;
import ru.sbt.mipt.oop.smarthome.serialization.SmartHomeDeserializer;
import ru.sbt.mipt.oop.smarthome.serialization.SmartHomeJsonDeserializer;

import java.util.Map;

@Configuration
public class ApplicationConfiguration {
    private static final String REMOTE_CONTROL_ID = "0";

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

    @Bean
    Command activateAlarmSystemCommand() {
        return new ActivateAlarmSystemCommand(alarmSystem());
    }

    @Bean
    Command alarmCommand() {
        return new AlarmCommand(alarmSystem());
    }

    @Bean
    Command closeHallDoorCommand() {
        return new CloseHallDoorCommand(smartHome());
    }

    @Bean
    Command turnOffLightCommand() {
        return new TurnOffLightCommand(smartHome());
    }

    @Bean
    Command turnOnHallLightCommand() {
        return new TurnOnHallLightCommand(smartHome());
    }

    @Bean
    Command turnOnLightCommand() {
        return new TurnOnLightCommand(smartHome());
    }

    @Bean
    RemoteController remoteController() {
        RemoteController remoteController = new RemoteController(REMOTE_CONTROL_ID);

        remoteController.setCommand("A", activateAlarmSystemCommand());
        remoteController.setCommand("B", alarmCommand());
        remoteController.setCommand("1", closeHallDoorCommand());
        remoteController.setCommand("2", turnOffLightCommand());
        remoteController.setCommand("3", turnOnHallLightCommand());
        remoteController.setCommand("4", turnOnLightCommand());

        return remoteController;
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteController(), REMOTE_CONTROL_ID);
        return remoteControlRegistry;
    }
}
