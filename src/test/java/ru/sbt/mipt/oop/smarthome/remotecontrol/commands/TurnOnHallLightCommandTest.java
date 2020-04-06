package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.sbt.mipt.oop.smarthome.components.Light;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.components.SmartHome;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TurnOnHallLightCommandTest {
    private Light bedroomLight0 = new Light("0", false);
    private Light hallLight1 = new Light("1", false);

    private TurnOnHallLightCommand command;

    @Before
    public void setUp() {
        SmartHome smartHome = new SmartHome(Arrays.asList(
                new Room(Collections.singletonList(hallLight1), "hall"),
                new Room(Collections.singletonList(bedroomLight0), "bedroom")
        ));

        command = new TurnOnHallLightCommand(smartHome);
    }

    @Test
    public void commandExecutionClosesHallLight() {
        command.execute();

        assertFalse(bedroomLight0.isOn());
        assertTrue(hallLight1.isOn());
    }
}