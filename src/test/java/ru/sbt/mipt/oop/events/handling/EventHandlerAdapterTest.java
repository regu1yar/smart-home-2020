package ru.sbt.mipt.oop.events.handling;

import com.coolcompany.smarthome.events.CCSensorEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.sbt.mipt.oop.events.types.SensorEvent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.sbt.mipt.oop.events.types.EventType.LIGHT_ON;

@RunWith(MockitoJUnitRunner.class)
public class EventHandlerAdapterTest {
    @Mock
    private EventHandler adapteeHandler;

    @InjectMocks
    private EventHandlerAdapter handlerAdapter;

    @Test
    public void handleEventTest() {
        String objectId = "0";
        ArgumentCaptor<SensorEvent> eventArgument = ArgumentCaptor.forClass(SensorEvent.class);

        handlerAdapter.handleEvent(new CCSensorEvent("LightIsOn", objectId));

        verify(adapteeHandler, times(1)).handleEvent(eventArgument.capture());
        assertEquals(LIGHT_ON, eventArgument.getValue().getType());
        assertEquals(objectId, eventArgument.getValue().getObjectId());
    }
}