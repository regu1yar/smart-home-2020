package ru.sbt.mipt.oop.smarthome.remotecontrol;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RemoteControllerTest {
    @Mock
    private Command command;

    private final String remoteControllerId = "0";
    private RemoteController remoteController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        remoteController = new RemoteController(remoteControllerId);
    }

    @Test
    public void setCommandAndTriggerIt() {
        String buttonCode = "A";
        remoteController.setCommand(buttonCode, command);
        remoteController.onButtonPressed(buttonCode, remoteControllerId);

        verify(command, times(1)).execute();
    }

    @Test
    public void notTriggerByOtherButton() {
        String buttonCode = "A";
        String anotherButtonCode = "B";
        remoteController.setCommand(buttonCode, command);
        remoteController.onButtonPressed(anotherButtonCode, remoteControllerId);

        verify(command, times(0)).execute();
    }

    @Test
    public void cannotSetCommandOnUnsupportedButtons() {
        String buttonCode = "Z0";
        remoteController.setCommand(buttonCode, command);
        remoteController.onButtonPressed(buttonCode, remoteControllerId);

        verify(command, times(0)).execute();
    }
}