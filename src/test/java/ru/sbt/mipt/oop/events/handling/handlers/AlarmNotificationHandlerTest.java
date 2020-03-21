package ru.sbt.mipt.oop.events.handling.handlers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.sbt.mipt.oop.events.types.Event;
import ru.sbt.mipt.oop.events.types.EventType;
import ru.sbt.mipt.oop.notifications.Notifier;
import ru.sbt.mipt.oop.security.AlarmSystem;

import static org.mockito.Mockito.*;
import static ru.sbt.mipt.oop.security.AlarmSystemState.*;

@RunWith(MockitoJUnitRunner.class)
public class AlarmNotificationHandlerTest {
    @Mock private AlarmSystem alarmSystem;
    @Mock private Notifier notifier;
    @Mock private Event event;

    @InjectMocks
    private AlarmNotificationHandler alarmNotificationHandler;

    @Test
    public void notifyIfAlarmSystemIsActivatedAndHandleSensorEvent() {
        when(alarmSystem.getState()).thenReturn(ACTIVATED);
        when(event.getType()).thenReturn(EventType.LIGHT_ON);

        alarmNotificationHandler.handleEvent(event);

        verify(notifier, times(1)).send(any());
    }

    @Test
    public void notifyIfAlarmSystemIsAlarmingAndHandleSensorEvent() {
        when(alarmSystem.getState()).thenReturn(ALARMING);
        when(event.getType()).thenReturn(EventType.LIGHT_ON);

        alarmNotificationHandler.handleEvent(event);

        verify(notifier, times(1)).send(any());
    }

    @Test
    public void notTriggerIfAlarmSystemIsDeactivated() {
        lenient().when(alarmSystem.getState()).thenReturn(DEACTIVATED);

        alarmNotificationHandler.handleEvent(event);

        verify(notifier, times(0)).send(any());
    }

    @Test
    public void notTriggerIfHandleOtherEvent() {
        lenient().when(alarmSystem.getState()).thenReturn(ACTIVATED);
        when(event.getType()).thenReturn(EventType.ALARM_DEACTIVATE);

        alarmNotificationHandler.handleEvent(event);

        verify(notifier, times(0)).send(any());
    }
}