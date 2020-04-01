package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.sbt.mipt.oop.smarthome.security.AlarmSystem;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ActivateAlarmSystemCommandTest {
    @Mock
    private AlarmSystem alarmSystem;

    @InjectMocks
    private ActivateAlarmSystemCommand command;

    @Test
    public void commandExecutionActivatesAlarm() {
        command.execute();

        verify(alarmSystem, times(1)).activate();
    }
}