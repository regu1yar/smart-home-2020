package ru.sbt.mipt.oop.events.handling.decorators;

import ru.sbt.mipt.oop.events.handling.EventHandler;
import ru.sbt.mipt.oop.events.handling.HandlerDecorator;
import ru.sbt.mipt.oop.events.types.Event;
import ru.sbt.mipt.oop.events.types.SensorEvent;
import ru.sbt.mipt.oop.security.AlarmSystem;
import ru.sbt.mipt.oop.security.ActivatedAlarmSystem;
import ru.sbt.mipt.oop.security.AlarmingAlarmSystem;
import ru.sbt.mipt.oop.security.DeactivatedAlarmSystem;

public class SecurityDecorator implements HandlerDecorator {
    private final EventHandler wrappee;
    private final AlarmSystem alarmSystem;

    public SecurityDecorator(EventHandler wrappee, AlarmSystem alarmSystem) {
        this.wrappee = wrappee;
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void handleEvent(Event event) {
        if (!(event instanceof SensorEvent) || alarmSystem.getState() instanceof DeactivatedAlarmSystem) {
            wrappee.handleEvent(event);
            return;
        } else if (alarmSystem.getState() instanceof ActivatedAlarmSystem) {
            alarmSystem.alarm();
            return;
        } else if (alarmSystem.getState() instanceof AlarmingAlarmSystem) {
            return;
        }

        wrappee.handleEvent(event);
    }
}
