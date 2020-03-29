package ru.sbt.mipt.oop.events.handling.decorators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.sbt.mipt.oop.events.handling.EventHandler;
import ru.sbt.mipt.oop.events.types.Event;
import ru.sbt.mipt.oop.events.types.EventType;
import ru.sbt.mipt.oop.security.ActivatedAlarmSystem;
import ru.sbt.mipt.oop.security.AlarmingAlarmSystem;
import ru.sbt.mipt.oop.security.DeactivatedAlarmSystem;
import ru.sbt.mipt.oop.security.SmartAlarmSystem;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SecurityDecoratorTest {
    @Mock private EventHandler wrappeeHandler;
    @Mock private SmartAlarmSystem alarmSystem;
    @Mock private Event event;

    @InjectMocks
    private SecurityDecorator decoratedHandler;

    @Test
    public void handleEventNormallyWhenAlarmSystemIsDeactivated() {
        when(alarmSystem.getState()).thenReturn(new DeactivatedAlarmSystem(alarmSystem));
        when(event.getType()).thenReturn(EventType.LIGHT_ON);
        
        decoratedHandler.handleEvent(event);
        
        verify(wrappeeHandler, times(1)).handleEvent(event);
    }

    @Test
    public void stopHandlingAndAlarmWhenAlarmSystemIsActivated() {
        when(alarmSystem.getState()).thenReturn(new ActivatedAlarmSystem(alarmSystem, "code"));
        when(event.getType()).thenReturn(EventType.LIGHT_ON);

        decoratedHandler.handleEvent(event);

        verify(wrappeeHandler, times(0)).handleEvent(event);
        verify(alarmSystem, times(1)).alarm();
    }

    @Test
    public void notHandlingWhenAlarmSystemIsAlarming() {
        when(alarmSystem.getState()).thenReturn(new AlarmingAlarmSystem(alarmSystem, "code"));
        when(event.getType()).thenReturn(EventType.LIGHT_ON);

        decoratedHandler.handleEvent(event);

        verify(wrappeeHandler, times(0)).handleEvent(event);
    }

    @Test
    public void notTriggersByOtherEvents() {
        lenient().when(alarmSystem.getState()).thenReturn(new ActivatedAlarmSystem(alarmSystem, "code"));
        when(event.getType()).thenReturn(EventType.ALARM_DEACTIVATE);

        decoratedHandler.handleEvent(event);

        verify(wrappeeHandler, times(1)).handleEvent(event);
        verify(alarmSystem, times(0)).alarm();
    }
}