package ru.sbt.mipt.oop.events.handling.handlers;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.handling.EventHandler;
import ru.sbt.mipt.oop.events.types.SensorEvent;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.events.types.EventType.*;

public class LightEventHandlerTest {
    private Light hallLight0 = new Light("0", false);
    private Light bedroomLight1 = new Light("1", true);

    private EventHandler lightEventHandler;

    @Before
    public void setUp() {
        SmartHome smartHome = new SmartHome(Arrays.asList(
                new Room(Collections.singletonList(hallLight0), "hall"),
                new Room(Collections.singletonList(bedroomLight1), "bedroom")
        ));

        lightEventHandler = new LightEventHandler(smartHome);
    }

    @Test
    public void turnOnLight() {
        lightEventHandler.handleEvent(new SensorEvent(LIGHT_ON, hallLight0.getId()));
        assertTrue(hallLight0.isOn());
    }

    @Test
    public void turnedOnLightStatedDoesntChangeWhenTurnedOn() {
        lightEventHandler.handleEvent(new SensorEvent(LIGHT_ON, bedroomLight1.getId()));
        assertTrue(bedroomLight1.isOn());
    }

    @Test
    public void dontTriggerOnNonExistingId() {
        lightEventHandler.handleEvent(new SensorEvent(LIGHT_ON, "2"));
        lightEventHandler.handleEvent(new SensorEvent(LIGHT_OFF, "3"));
        assertFalse(hallLight0.isOn());
        assertTrue(bedroomLight1.isOn());
    }

    @Test
    public void turnOffLight() {
        lightEventHandler.handleEvent(new SensorEvent(LIGHT_OFF, bedroomLight1.getId()));
        assertFalse(bedroomLight1.isOn());
    }

    @Test
    public void turnedOffLightStatedDoesntChangeWhenTurnedOff() {
        lightEventHandler.handleEvent(new SensorEvent(LIGHT_OFF, hallLight0.getId()));
        assertFalse(hallLight0.isOn());
    }

    @Test
    public void dontTriggerOnOtherEvents() {
        lightEventHandler.handleEvent(new SensorEvent(DOOR_OPEN, hallLight0.getId()));
        assertFalse(hallLight0.isOn());
        assertTrue(bedroomLight1.isOn());
    }
}