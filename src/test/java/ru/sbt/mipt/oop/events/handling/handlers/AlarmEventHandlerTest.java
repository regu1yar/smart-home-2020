package ru.sbt.mipt.oop.events.handling.handlers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.sbt.mipt.oop.events.types.AlarmSystemEvent;
import ru.sbt.mipt.oop.events.types.EventType;
import ru.sbt.mipt.oop.security.AlarmSystem;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AlarmEventHandlerTest {
    @Mock
    private AlarmSystem alarmSystem;

    @InjectMocks
    private AlarmEventHandler alarmEventHandler;

    @Test
    public void activateEventTriggersAlarmActivation() {
        String password = "password";
        alarmEventHandler.handleEvent(new AlarmSystemEvent(EventType.ALARM_ACTIVATE, password));

        verify(alarmSystem, times(1)).activate(password);
    }

    @Test
    public void deactivateEventTriggersAlarmDeactivation() {
        String password = "password";
        alarmEventHandler.handleEvent(new AlarmSystemEvent(EventType.ALARM_DEACTIVATE, password));

        verify(alarmSystem, times(1)).deactivate(password);
    }
}