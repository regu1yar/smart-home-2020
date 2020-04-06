package ru.sbt.mipt.oop.smarthome.events.producing;

import ru.sbt.mipt.oop.smarthome.events.types.AlarmSystemEvent;
import ru.sbt.mipt.oop.smarthome.events.types.Event;
import ru.sbt.mipt.oop.smarthome.events.types.SensorEvent;
import ru.sbt.mipt.oop.smarthome.events.types.EventType;

import static ru.sbt.mipt.oop.smarthome.events.types.EventType.*;

public class RandomEventProducer implements EventProducer {
    @Override
    public Event getNextEvent() {
        if (Math.random() < 0.05) return null; // null means end of event stream
        if (Math.random() > Integer.valueOf(getAlarmSystemEvents().size()).doubleValue() / EventType.values().length) {
            return getAlarmSystemEvent();
        } else if (Math.random() > Integer.valueOf(getLightEvents().size()).doubleValue() / EventType.values().length) {
            return getLightEvent();
        } else {
            return getDoorEvent();
        }
    }

    private Event getAlarmSystemEvent() {
        EventType eventType = (EventType) getAlarmSystemEvents().toArray() [(int) (getAlarmSystemEvents().size() * Math.random())];
        String code = "" + ((int) (2 * Math.random()));
        return new AlarmSystemEvent(eventType, code);
    }

    private Event getLightEvent() {
        EventType eventType = (EventType) getLightEvents().toArray() [(int) (getLightEvents().size() * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(eventType, objectId);
    }

    private Event getDoorEvent() {
        EventType eventType = (EventType) getDoorEvents().toArray() [(int) (getDoorEvents().size() * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(eventType, objectId);
    }
}
