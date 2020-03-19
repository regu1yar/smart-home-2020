package ru.sbt.mipt.oop.events.handlers;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.SensorEvent;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class LightEventHandlerTest {
    private Light hallLight0 = new Light("0", false);
    private Light bedroomLight1 = new Light("1", false);
    private Light bathroomLight2 = new Light("2", true);
    private Door bedroomDoor0 = new Door("0", true);
    private Door hallDoor1 = new Door("1", false);

    private EventHandler lightEventHandler;

    @Before
    public void setUp() {
        SmartHome smartHome = new SmartHome(Arrays.asList(
                new Room(Arrays.asList(hallLight0, hallDoor1), "hall"),
                new Room(Arrays.asList(bedroomLight1, bedroomDoor0), "bedroom"),
                new Room(Collections.singletonList(bathroomLight2), "bathroom")
        ));

        lightEventHandler = new LightEventHandler(smartHome);
    }

    @Test
    public void handleLightOnEvent() {
        checkInitialState();
        lightEventHandler.handleEvent(new SensorEvent(LIGHT_ON, "0"));
        assertEquals(true, hallLight0.isOn());
        assertEquals(false, bedroomLight1.isOn());
        assertEquals(true, bathroomLight2.isOn());
        assertEquals(true, bedroomDoor0.isOpen());
        assertEquals(false, hallDoor1.isOpen());
    }

    @Test
    public void handleLightOnEventOnTurnedOnLight() {
        checkInitialState();
        lightEventHandler.handleEvent(new SensorEvent(LIGHT_ON, "2"));
        checkInitialState();
    }

    @Test
    public void handleLightOnEventOnNonExistingId() {
        checkInitialState();
        lightEventHandler.handleEvent(new SensorEvent(LIGHT_ON, "3"));
        checkInitialState();
    }

    @Test
    public void handleLightOffEvent() {
        checkInitialState();
        lightEventHandler.handleEvent(new SensorEvent(LIGHT_OFF, "2"));
        assertEquals(false, hallLight0.isOn());
        assertEquals(false, bedroomLight1.isOn());
        assertEquals(false, bathroomLight2.isOn());
        assertEquals(true, bedroomDoor0.isOpen());
        assertEquals(false, hallDoor1.isOpen());
    }

    @Test
    public void handleLightOffEventOnTurnedOffLight() {
        checkInitialState();
        lightEventHandler.handleEvent(new SensorEvent(LIGHT_OFF, "0"));
        checkInitialState();
    }

    @Test
    public void handleLightOffEventOnNonExistingId() {
        checkInitialState();
        lightEventHandler.handleEvent(new SensorEvent(LIGHT_OFF, "3"));
        checkInitialState();
    }

    @Test
    public void handleOtherEvent() {
        checkInitialState();
        lightEventHandler.handleEvent(new SensorEvent(DOOR_OPEN, "0"));
        checkInitialState();
    }

    private void checkInitialState() {
        assertEquals(false, hallLight0.isOn());
        assertEquals(false, bedroomLight1.isOn());
        assertEquals(true, bathroomLight2.isOn());
        assertEquals(true, bedroomDoor0.isOpen());
        assertEquals(false, hallDoor1.isOpen());
    }
}