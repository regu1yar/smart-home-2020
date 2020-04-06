package ru.sbt.mipt.oop.smarthome.serialization;

import ru.sbt.mipt.oop.smarthome.components.SmartHome;

public interface SmartHomeSerializer {
    void serialize(SmartHome smartHome);
}
