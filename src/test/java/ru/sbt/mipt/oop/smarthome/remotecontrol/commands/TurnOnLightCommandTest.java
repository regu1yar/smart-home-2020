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
public class TurnOnLightCommandTest {
    private Light hallLight0 = new Light("0", false);
    private Light bedroomLight1 = new Light("1", true);

    private TurnOnLightCommand command;

    @Before
    public void setUp() {
        SmartHome smartHome = new SmartHome(Arrays.asList(
                new Room(Collections.singletonList(hallLight0), "hall"),
                new Room(Collections.singletonList(bedroomLight1), "bedroom")
        ));

        command = new TurnOnLightCommand(smartHome);
    }

    @Test
    public void commandExecutionTurnsOffAllLights() {
        command.execute();

        assertTrue(hallLight0.isOn());
        assertTrue(bedroomLight1.isOn());
    }
}