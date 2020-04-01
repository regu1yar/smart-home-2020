package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.sbt.mipt.oop.smarthome.components.Door;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.components.SmartHome;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CloseHallDoorCommandTest {
    private Door bedroomDoor0 = new Door("0", true);
    private Door hallDoor1 = new Door("1", true);

    private CloseHallDoorCommand command;

    @Before
    public void setUp() {
        SmartHome smartHome = new SmartHome(Arrays.asList(
                new Room(Collections.singletonList(hallDoor1), "hall"),
                new Room(Collections.singletonList(bedroomDoor0), "bedroom")
        ));

        command = new CloseHallDoorCommand(smartHome);
    }

    @Test
    public void commandExecutionClosesHallDoor() {
        command.execute();

        assertFalse(hallDoor1.isOpen());
        assertTrue(bedroomDoor0.isOpen());
    }
}