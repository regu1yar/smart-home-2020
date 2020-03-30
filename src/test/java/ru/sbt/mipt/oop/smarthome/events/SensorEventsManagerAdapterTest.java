package ru.sbt.mipt.oop.smarthome.events;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import ru.sbt.mipt.oop.smarthome.events.handling.EventHandler;
import ru.sbt.mipt.oop.smarthome.events.handling.EventHandlerAdapter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SensorEventsManagerAdapterTest {
    @Mock private SensorEventsManager adapteeManager;
    @Mock private EventHandler eventHandler;

    private EventProcessor managerAdapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        managerAdapter = new SensorEventsManagerAdapter(adapteeManager);
    }

    @Test
    public void startProcessingTest() {
        managerAdapter.startProcessing();

        verify(adapteeManager, times(1)).start();
    }

    @Test
    public void registerHandlerTest() {
        ArgumentCaptor<com.coolcompany.smarthome.events.EventHandler> handlerArgument =
                ArgumentCaptor.forClass(com.coolcompany.smarthome.events.EventHandler.class);

        managerAdapter.registerHandler(eventHandler);

        verify(adapteeManager, times(1)).registerEventHandler(handlerArgument.capture());
        assertTrue(handlerArgument.getValue() instanceof EventHandlerAdapter);
    }
}