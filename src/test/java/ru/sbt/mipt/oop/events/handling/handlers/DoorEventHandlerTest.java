package ru.sbt.mipt.oop.events.handling.handlers;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.handling.EventHandler;
import ru.sbt.mipt.oop.events.handling.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.events.types.SensorEvent;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.events.types.EventType.*;

public class DoorEventHandlerTest {
    private Door bedroomDoor0 = new Door("0", true);
    private Door hallDoor1 = new Door("1", false);
    SmartHome smartHome;

    private EventHandler doorEventHandler;

    @Before
    public void setUp() {
        smartHome = new SmartHome(Arrays.asList(
                new Room(Collections.singletonList(hallDoor1), "hall"),
                new Room(Collections.singletonList(bedroomDoor0), "bedroom")
        ));

        doorEventHandler = new DoorEventHandler(smartHome);
    }

    @Test
    public void openClosedDoor() {
        doorEventHandler.handleEvent(new SensorEvent(DOOR_OPEN, hallDoor1.getId()));
        assertTrue(hallDoor1.isOpen());
    }

    @Test
    public void openedDoorStatedDoesntChangeWhenOpened() {
        doorEventHandler.handleEvent(new SensorEvent(DOOR_OPEN, bedroomDoor0.getId()));
        assertTrue(bedroomDoor0.isOpen());
    }

    @Test
    public void dontTriggerOnNonExistingId() {
        doorEventHandler.handleEvent(new SensorEvent(DOOR_OPEN, "2"));
        doorEventHandler.handleEvent(new SensorEvent(DOOR_CLOSED, "3"));
        assertFalse(hallDoor1.isOpen());
        assertTrue(bedroomDoor0.isOpen());
    }

    @Test
    public void closeOpenedDoor() {
        doorEventHandler.handleEvent(new SensorEvent(DOOR_CLOSED, bedroomDoor0.getId()));
        assertFalse(bedroomDoor0.isOpen());
    }

    @Test
    public void closedDoorStatedDoesntChangeWhenClosed() {
        doorEventHandler.handleEvent(new SensorEvent(DOOR_CLOSED, hallDoor1.getId()));
        assertFalse(hallDoor1.isOpen());
    }

    @Test
    public void dontTriggerOnOtherEvents() {
        doorEventHandler.handleEvent(new SensorEvent(LIGHT_ON, hallDoor1.getId()));
        assertFalse(hallDoor1.isOpen());
        assertTrue(bedroomDoor0.isOpen());
    }
}