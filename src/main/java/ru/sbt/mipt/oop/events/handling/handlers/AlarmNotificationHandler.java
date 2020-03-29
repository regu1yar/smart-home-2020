package ru.sbt.mipt.oop.events.handling.handlers;

import ru.sbt.mipt.oop.events.handling.EventHandler;
import ru.sbt.mipt.oop.events.types.Event;
import ru.sbt.mipt.oop.notifications.Notifier;
import ru.sbt.mipt.oop.security.AlarmSystem;
import ru.sbt.mipt.oop.security.DeactivatedAlarmSystem;

import static ru.sbt.mipt.oop.events.types.EventType.getSensorEvents;

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
        if (getSensorEvents().contains(event.getType()) &&
                !(alarmSystem.getState() instanceof DeactivatedAlarmSystem)) {
            notifier.send(NOTIFICATION_MESSAGE);
        }
    }
}
