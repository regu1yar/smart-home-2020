package ru.sbt.mipt.oop.events;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ru.sbt.mipt.oop.events.handlers.EventHandler;
import ru.sbt.mipt.oop.events.producers.EventProducer;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static ru.sbt.mipt.oop.events.SensorEventType.*;

@RunWith(MockitoJUnitRunner.class)
public class EventProcessorTest {
    @Mock private EventProducer eventProducer;
    @Mock private EventHandler handler;

    private EventProcessor eventProcessor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        eventProcessor = new EventProcessor(Collections.singletonList(handler), eventProducer);
    }

    @Test
    public void getNextEventTimesWhileProcessing() {
        when(eventProducer.getNextSensorEvent()).thenReturn(
                new SensorEvent(DOOR_OPEN, "0"),
                new SensorEvent(DOOR_CLOSED, "1"),
                new SensorEvent(LIGHT_OFF, "2"),
                new SensorEvent(DOOR_CLOSED, "0"),
                null
        );

        eventProcessor.startProcessing();

        verify(eventProducer, times(5)).getNextSensorEvent();
        verify(handler, times(4)).handleEvent(any());
    }
}