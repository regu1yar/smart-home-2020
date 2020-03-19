package ru.sbt.mipt.oop.components;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SmartHomeTest {
    @Mock private ActionableComponent homeComponent1;
    @Mock private ActionableComponent homeComponent2;
    SmartHome smartHome;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        smartHome = new SmartHome(Arrays.asList(homeComponent1, homeComponent2));
    }

    @Test
    public void executeTest() {
        smartHome.execute(component -> {});
        verify(homeComponent1, times(1)).execute(any());
        verify(homeComponent2, times(1)).execute(any());
    }
}