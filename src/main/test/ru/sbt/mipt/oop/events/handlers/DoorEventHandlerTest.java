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

public class DoorEventHandlerTest {
    Light hallLight0 = new Light("0", false);
    Light bedroomLight1 = new Light("1", false);
    Light bathroomLight2 = new Light("2", true);
    Door bedroomDoor0 = new Door("0", true);
    Door hallDoor1 = new Door("1", false);
    Door kitchenDoor2 = new Door("2", false);
    SmartHome smartHome;

    EventHandler doorEventHandler;

    @Before
    public void setUp() throws Exception {
        smartHome = new SmartHome(Arrays.asList(
                new Room(Arrays.asList(hallLight0, hallDoor1), "hall"),
                new Room(Arrays.asList(bedroomLight1, bedroomDoor0), "bedroom"),
                new Room(Collections.singletonList(bathroomLight2), "bathroom"),
                new Room(Collections.singletonList(kitchenDoor2), "kitchen")
        ));

        doorEventHandler = new DoorEventHandler(smartHome);
    }

    @Test
    public void handleDoorOpenEvent() {
        checkInitialState();
        doorEventHandler.handleEvent(new SensorEvent(DOOR_OPEN, "2"));
        assertEquals(false, hallLight0.isOn());
        assertEquals(false, bedroomLight1.isOn());
        assertEquals(true, bathroomLight2.isOn());
        assertEquals(true, bedroomDoor0.isOpen());
        assertEquals(false, hallDoor1.isOpen());
        assertEquals(true, kitchenDoor2.isOpen());
    }

    @Test
    public void handleDoorOpenEventOnOpenDoor() {
        checkInitialState();
        doorEventHandler.handleEvent(new SensorEvent(DOOR_OPEN, "0"));
        checkInitialState();
    }

    @Test
    public void handleDoorOpenEventOnNonExistingId() {
        checkInitialState();
        doorEventHandler.handleEvent(new SensorEvent(DOOR_OPEN, "3"));
        checkInitialState();
    }

    @Test
    public void handleDoorClosedEvent() {
        checkInitialState();
        doorEventHandler.handleEvent(new SensorEvent(DOOR_CLOSED, "0"));
        assertEquals(false, hallLight0.isOn());
        assertEquals(false, bedroomLight1.isOn());
        assertEquals(true, bathroomLight2.isOn());
        assertEquals(false, bedroomDoor0.isOpen());
        assertEquals(false, hallDoor1.isOpen());
        assertEquals(false, kitchenDoor2.isOpen());
    }

    @Test
    public void handleDoorClosedEventOnClosedDoor() {
        checkInitialState();
        doorEventHandler.handleEvent(new SensorEvent(DOOR_CLOSED, "2"));
        checkInitialState();
    }

    @Test
    public void handleDoorClosedEventOnNonExistingId() {
        checkInitialState();
        doorEventHandler.handleEvent(new SensorEvent(DOOR_CLOSED, "3"));
        checkInitialState();
    }

    @Test
    public void handleOtherEvent() {
        checkInitialState();
        doorEventHandler.handleEvent(new SensorEvent(LIGHT_ON, "0"));
        checkInitialState();
    }

    private void checkInitialState() {
        assertEquals(false, hallLight0.isOn());
        assertEquals(false, bedroomLight1.isOn());
        assertEquals(true, bathroomLight2.isOn());
        assertEquals(true, bedroomDoor0.isOpen());
        assertEquals(false, hallDoor1.isOpen());
        assertEquals(false, kitchenDoor2.isOpen());
    }
}