package ru.sbt.mipt.oop.events.handling.decorators;

import ru.sbt.mipt.oop.events.handling.EventHandler;
import ru.sbt.mipt.oop.events.handling.HandlerDecorator;
import ru.sbt.mipt.oop.events.types.Event;
import ru.sbt.mipt.oop.notifications.Notifier;
import ru.sbt.mipt.oop.security.AlarmSystem;
import ru.sbt.mipt.oop.security.AlarmSystemState;

import static ru.sbt.mipt.oop.security.AlarmSystemState.*;

public class SecurityDecorator extends HandlerDecorator {
    private final AlarmSystem alarmSystem;

    public SecurityDecorator(EventHandler wrappee, AlarmSystem alarmSystem) {
        super(wrappee);
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void handleEvent(Event event) {
        if (alarmSystem.getState() == ACTIVATED) {
            alarmSystem.alarm();
            return;
        } else if (alarmSystem.getState() == ALARMING) {
            return;
        }

        super.handleEvent(event);
    }
}
