package ru.sbt.mipt.oop.events.handling.handlers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.sbt.mipt.oop.events.types.AlarmSystemEvent;
import ru.sbt.mipt.oop.events.types.Event;
import ru.sbt.mipt.oop.events.types.EventType;
import ru.sbt.mipt.oop.events.types.SensorEvent;
import ru.sbt.mipt.oop.notifications.Notifier;
import ru.sbt.mipt.oop.security.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AlarmNotificationHandlerTest {
    @Mock private SmartAlarmSystem alarmSystem;
    @Mock private Notifier notifier;

    @InjectMocks
    private AlarmNotificationHandler alarmNotificationHandler;

    @Test
    public void notifyIfAlarmSystemIsActivatedAndHandleSensorEvent() {
        when(alarmSystem.getState()).thenReturn(new ActivatedAlarmSystem(alarmSystem, "code"));
        Event event = new SensorEvent(EventType.LIGHT_ON, "0");

        alarmNotificationHandler.handleEvent(event);

        verify(notifier, times(1)).send(any());
    }

    @Test
    public void notifyIfAlarmSystemIsAlarmingAndHandleSensorEvent() {
        when(alarmSystem.getState()).thenReturn(new AlarmingAlarmSystem(alarmSystem, "code"));
        Event event = new SensorEvent(EventType.LIGHT_ON, "0");

        alarmNotificationHandler.handleEvent(event);

        verify(notifier, times(1)).send(any());
    }

    @Test
    public void notTriggerIfAlarmSystemIsDeactivated() {
        lenient().when(alarmSystem.getState()).thenReturn(new DeactivatedAlarmSystem(alarmSystem));
        Event event = new SensorEvent(EventType.LIGHT_ON, "0");

        alarmNotificationHandler.handleEvent(event);

        verify(notifier, times(0)).send(any());
    }

    @Test
    public void notTriggerIfHandleOtherEvent() {
        lenient().when(alarmSystem.getState()).thenReturn(new ActivatedAlarmSystem(alarmSystem, "code"));
        Event event = new AlarmSystemEvent(EventType.ALARM_DEACTIVATE, "code");

        alarmNotificationHandler.handleEvent(event);

        verify(notifier, times(0)).send(any());
    }
}