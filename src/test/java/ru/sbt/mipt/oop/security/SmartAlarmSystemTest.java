package ru.sbt.mipt.oop.security;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.security.AlarmSystemState.*;

public class SmartAlarmSystemTest {
    private AlarmSystem alarmSystem;

    @Before
    public void setUp() {
        alarmSystem = new SmartAlarmSystem();
    }

    @Test
    public void initiallyDeactivated() {
        assertEquals(DEACTIVATED, alarmSystem.getState());
    }

    @Test
    public void activatedAfterActivate() {
        alarmSystem.activate("password");

        assertEquals(ACTIVATED, alarmSystem.getState());
    }

    @Test
    public void activateAndDeactivateWithRightPassword() {
        String password = "password";
        alarmSystem.activate(password);
        alarmSystem.deactivate(password);

        assertEquals(DEACTIVATED, alarmSystem.getState());
    }

    @Test
    public void triggerAlarmAfterDeactivatingWithWrongPassword() {
        String password = "password";
        String wrongPassword = "wrongPassword";
        alarmSystem.activate(password);
        alarmSystem.deactivate(wrongPassword);

        assertEquals(ALARMING, alarmSystem.getState());
    }

    @Test
    public void triggerAlarmManuallyFromDeactivatedState() {
        alarmSystem.alarm();

        assertEquals(ALARMING, alarmSystem.getState());
    }

    @Test
    public void triggerAlarmManuallyFromActivatedState() {
        alarmSystem.activate("password");
        alarmSystem.alarm();

        assertEquals(ALARMING, alarmSystem.getState());
    }
}