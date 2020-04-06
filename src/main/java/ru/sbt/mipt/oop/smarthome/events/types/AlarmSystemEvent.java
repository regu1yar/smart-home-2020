package ru.sbt.mipt.oop.smarthome.events.types;

public class AlarmSystemEvent implements Event {
    private final EventType type;
    private final String code;

    public AlarmSystemEvent(EventType type, String code) {
        this.type = type;
        this.code = code;
    }

    public EventType getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", code='" + code + '\'' +
                '}';
    }
}
