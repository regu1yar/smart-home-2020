package ru.sbt.mipt.oop.components;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RoomTest {
    @Mock private ActionableComponent roomComponent1;
    @Mock private ActionableComponent roomComponent2;
    Room room;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        room = new Room(Arrays.asList(roomComponent1, roomComponent2), "room");
    }

    @Test
    public void executeTest() {
        room.execute(component -> {});
        verify(roomComponent1, times(1)).execute(any());
        verify(roomComponent2, times(1)).execute(any());
    }
}