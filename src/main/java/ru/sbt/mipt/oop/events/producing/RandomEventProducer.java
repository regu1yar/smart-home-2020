package ru.sbt.mipt.oop.events.producing;

import ru.sbt.mipt.oop.events.types.AlarmSystemEvent;
import ru.sbt.mipt.oop.events.types.Event;
import ru.sbt.mipt.oop.events.types.SensorEvent;
import ru.sbt.mipt.oop.events.types.EventType;

import static ru.sbt.mipt.oop.events.types.EventType.*;

public class RandomEventProducer implements EventProducer {
    @Override
    public Event getNextEvent() {
        if (Math.random() < 0.05) return null; // null means end of event stream
        if (Math.random() <= Integer.valueOf(getSensorEvents().size()).doubleValue() / EventType.values().length) {
            return getSensorEvent();
        } else {
            return getAlarmSystemEvent();
        }
    }

    private Event getAlarmSystemEvent() {
        EventType eventType = (EventType) getAlarmSystemEvents().toArray() [(int) (getAlarmSystemEvents().size() * Math.random())];
        String code = "" + ((int) (2 * Math.random()));
        return new AlarmSystemEvent(eventType, code);
    }

    private Event getSensorEvent() {
        EventType eventType = (EventType) getSensorEvents().toArray() [(int) (getSensorEvents().size() * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(eventType, objectId);
    }
}
