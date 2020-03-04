package ru.sbt.mipt.oop.serialization;

import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;

public interface SmartHomeSerializer {
    public void serialize(SmartHome smartHome, String target) throws IOException;
    public SmartHome deserialize(String source) throws IOException;
}
