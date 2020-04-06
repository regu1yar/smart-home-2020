package ru.sbt.mipt.oop.smarthome.events.handling.handlers;

import ru.sbt.mipt.oop.smarthome.events.handling.EventHandler;
import ru.sbt.mipt.oop.smarthome.events.types.Event;
import ru.sbt.mipt.oop.smarthome.events.types.SensorEvent;
import ru.sbt.mipt.oop.smarthome.notifications.Notifier;
import ru.sbt.mipt.oop.smarthome.security.AlarmSystem;
import ru.sbt.mipt.oop.smarthome.security.DeactivatedAlarmSystem;

public class AlarmNotificationHandler implements EventHandler {
    private final AlarmSystem alarmSystem;
    private final Notifier notifier;

    private final static String NOTIFICATION_MESSAGE = "ALARM!!!";

    public AlarmNotificationHandler(AlarmSystem alarmSystem, Notifier notifier) {
        this.alarmSystem = alarmSystem;
        this.notifier = notifier;
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof SensorEvent && !(alarmSystem.getState() instanceof DeactivatedAlarmSystem)) {
            notifier.send(NOTIFICATION_MESSAGE);
        }
    }
}
