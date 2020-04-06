package ru.sbt.mipt.oop.smarthome.events.handling.handlers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ru.sbt.mipt.oop.smarthome.commands.CommandSender;
import ru.sbt.mipt.oop.smarthome.components.Door;
import ru.sbt.mipt.oop.smarthome.components.Light;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.components.SmartHome;
import ru.sbt.mipt.oop.smarthome.events.handling.EventHandler;
import ru.sbt.mipt.oop.smarthome.events.types.SensorEvent;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.sbt.mipt.oop.smarthome.events.types.EventType.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CloseHallDoorEventHandlerTest {
    @Mock
    private CommandSender commandSender;

    private Light hallLight0 = new Light("0", true);
    private Light bedroomLight1 = new Light("1", false);
    private Door bedroomDoor0 = new Door("0", false);
    private Door hallDoor1 = new Door("1", true);
    SmartHome smartHome;

    private EventHandler hallDoorEventHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        smartHome = new SmartHome(Arrays.asList(
                new Room(Arrays.asList(hallLight0, hallDoor1), "hall"),
                new Room(Arrays.asList(bedroomLight1, bedroomDoor0), "bedroom")
        ));

        hallDoorEventHandler = new CloseHallDoorEventHandler(smartHome, commandSender);
    }

    @Test
    public void closeHallDoorAndTurnOffLights() {
        hallDoorEventHandler.handleEvent(new SensorEvent(DOOR_CLOSED, hallDoor1.getId()));

        smartHome.execute(component -> {
            if (!(component instanceof Light)) return;
            Light light = (Light) component;
            assertFalse(light.isOn());
        });
        assertTrue(hallDoor1.isOpen());
        verify(commandSender, times(2)).sendCommand(any());
    }

    @Test
    public void dontTriggerOnOtherDoors() {
        hallDoorEventHandler.handleEvent(new SensorEvent(DOOR_CLOSED, bedroomDoor0.getId()));
        assertTrue(hallLight0.isOn());
    }

    @Test
    public void dontTriggerOnNonExistingId() {
        hallDoorEventHandler.handleEvent(new SensorEvent(DOOR_CLOSED, "3"));
        assertTrue(hallLight0.isOn());
    }

    @Test
    public void dontTriggerOnOtherEvents() {
        hallDoorEventHandler.handleEvent(new SensorEvent(DOOR_OPEN, hallDoor1.getId()));
        assertTrue(hallLight0.isOn());
    }
}