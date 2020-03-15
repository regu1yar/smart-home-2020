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

import static org.junit.Assert.assertEquals;
import static ru.sbt.mipt.oop.events.SensorEventType.*;
import static org.junit.Assert.*;

public class CloseHallDoorEventHandlerTest {
    private Light hallLight0 = new Light("0", true);
    private Light bedroomLight1 = new Light("1", false);
    private Light bathroomLight2 = new Light("2", true);
    private Door bedroomDoor0 = new Door("0", true);
    private Door hallDoor1 = new Door("1", true);
    private Door kitchenDoor2 = new Door("2", false);

    private EventHandler hallDoorEventHandler;

    @Before
    public void setUp() {
        SmartHome smartHome = new SmartHome(Arrays.asList(
                new Room(Arrays.asList(hallLight0, hallDoor1), "hall"),
                new Room(Arrays.asList(bedroomLight1, bedroomDoor0), "bedroom"),
                new Room(Collections.singletonList(bathroomLight2), "bathroom"),
                new Room(Collections.singletonList(kitchenDoor2), "kitchen")
        ));

        hallDoorEventHandler = new CloseHallDoorEventHandler(smartHome);
    }

    @Test
    public void handleHallDoorClosedEvent() {
        checkInitialState();
        assertTrue(hallDoor1.isOpen());

        hallDoorEventHandler.handleEvent(new SensorEvent(DOOR_CLOSED, "1"));
        assertEquals(false, hallLight0.isOn());
        assertEquals(false, bedroomLight1.isOn());
        assertEquals(false, bathroomLight2.isOn());
        assertEquals(true, bedroomDoor0.isOpen());
        assertEquals(true, hallDoor1.isOpen());
        assertEquals(false, kitchenDoor2.isOpen());
    }

    @Test
    public void handleHallDoorClosedEventOnClosedDoor() {
        checkInitialState();

        hallDoor1.setOpen(false);
        assertEquals(true, hallLight0.isOn());
        assertEquals(false, bedroomLight1.isOn());
        assertEquals(true, bathroomLight2.isOn());
        assertEquals(true, bedroomDoor0.isOpen());
        assertEquals(false, hallDoor1.isOpen());
        assertEquals(false, kitchenDoor2.isOpen());

        hallDoorEventHandler.handleEvent(new SensorEvent(DOOR_CLOSED, "2"));
        assertEquals(true, hallLight0.isOn());
        assertEquals(false, bedroomLight1.isOn());
        assertEquals(true, bathroomLight2.isOn());
        assertEquals(true, bedroomDoor0.isOpen());
        assertEquals(false, hallDoor1.isOpen());
        assertEquals(false, kitchenDoor2.isOpen());
    }

    @Test
    public void handleDoorClosedEventOnNonExistingId() {
        checkInitialState();
        hallDoorEventHandler.handleEvent(new SensorEvent(DOOR_CLOSED, "3"));
        checkInitialState();
    }

    @Test
    public void handleOtherEvents() {
        checkInitialState();
        hallDoorEventHandler.handleEvent(new SensorEvent(LIGHT_ON, "0"));
        checkInitialState();
        hallDoorEventHandler.handleEvent(new SensorEvent(DOOR_OPEN, "2"));
        checkInitialState();
    }


    private void checkInitialState() {
        assertEquals(true, hallLight0.isOn());
        assertEquals(false, bedroomLight1.isOn());
        assertEquals(true, bathroomLight2.isOn());
        assertEquals(true, bedroomDoor0.isOpen());
        assertEquals(true, hallDoor1.isOpen());
        assertEquals(false, kitchenDoor2.isOpen());
    }
}