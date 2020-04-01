package ru.sbt.mipt.oop.smarthome.events.handling.decorators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.sbt.mipt.oop.smarthome.events.handling.EventHandler;
import ru.sbt.mipt.oop.smarthome.events.types.AlarmSystemEvent;
import ru.sbt.mipt.oop.smarthome.events.types.Event;
import ru.sbt.mipt.oop.smarthome.events.types.EventType;
import ru.sbt.mipt.oop.smarthome.events.types.SensorEvent;
import ru.sbt.mipt.oop.smarthome.security.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SecurityDecoratorTest {
    @Mock private EventHandler wrappeeHandler;
    @Mock private SmartAlarmSystem alarmSystem;

    @InjectMocks
    private SecurityDecorator decoratedHandler;

    @Test
    public void handleEventNormallyWhenAlarmSystemIsDeactivated() {
        when(alarmSystem.getState()).thenReturn(new DeactivatedAlarmSystem(alarmSystem, "defaultCode"));
        Event event = new SensorEvent(EventType.LIGHT_ON, "0");

        decoratedHandler.handleEvent(event);
        
        verify(wrappeeHandler, times(1)).handleEvent(event);
    }

    @Test
    public void stopHandlingAndAlarmWhenAlarmSystemIsActivated() {
        when(alarmSystem.getState()).thenReturn(new ActivatedAlarmSystem(alarmSystem, "code", "defaultCode"));
        Event event = new SensorEvent(EventType.LIGHT_ON, "0");

        decoratedHandler.handleEvent(event);

        verify(wrappeeHandler, times(0)).handleEvent(event);
        verify(alarmSystem, times(1)).alarm();
    }

    @Test
    public void notHandlingWhenAlarmSystemIsAlarming() {
        when(alarmSystem.getState()).thenReturn(new AlarmingAlarmSystem(alarmSystem, "code", "defaultCode"));
        Event event = new SensorEvent(EventType.LIGHT_ON, "0");

        decoratedHandler.handleEvent(event);

        verify(wrappeeHandler, times(0)).handleEvent(event);
    }

    @Test
    public void notTriggersByOtherEvents() {
        lenient().when(alarmSystem.getState()).thenReturn(new ActivatedAlarmSystem(alarmSystem, "code", "defaultCode"));
        Event event = new AlarmSystemEvent(EventType.ALARM_DEACTIVATE, "code");

        decoratedHandler.handleEvent(event);

        verify(wrappeeHandler, times(1)).handleEvent(event);
        verify(alarmSystem, times(0)).alarm();
    }
}