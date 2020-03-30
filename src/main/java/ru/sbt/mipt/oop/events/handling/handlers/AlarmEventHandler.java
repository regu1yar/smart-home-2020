package ru.sbt.mipt.oop.events.handling.handlers;

import ru.sbt.mipt.oop.events.handling.EventHandler;
import ru.sbt.mipt.oop.events.types.AlarmSystemEvent;
import ru.sbt.mipt.oop.events.types.Event;
import ru.sbt.mipt.oop.security.AlarmSystem;

public class AlarmEventHandler implements EventHandler {
    AlarmSystem alarmSystem;

    public AlarmEventHandler(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void handleEvent(Event event) {
        if (!(event instanceof AlarmSystemEvent)) return;

        AlarmSystemEvent alarmEvent = (AlarmSystemEvent) event;
        switch (alarmEvent.getType()) {
            case ALARM_ACTIVATE:
                activateAlarmSystem(alarmEvent.getCode());
                break;
            case ALARM_DEACTIVATE:
                deactivateAlarmSystem(alarmEvent.getCode());
                break;
        }
    }

    private void activateAlarmSystem(String code) {
        alarmSystem.activate(code);
    }

    private void deactivateAlarmSystem(String code) {
        alarmSystem.deactivate(code);
    }
}
