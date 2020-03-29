package ru.sbt.mipt.oop.security;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SmartAlarmSystemTest {
    private AlarmSystem alarmSystem;

    @Before
    public void setUp() {
        alarmSystem = new SmartAlarmSystem();
    }

    @Test
    public void initiallyDeactivated() {
        assertTrue(alarmSystem.getState() instanceof DeactivatedAlarmSystem);
    }

    @Test
    public void activatedAfterActivate() {
        alarmSystem.activate("password");

        assertTrue(alarmSystem.getState() instanceof ActivatedAlarmSystem);
    }

    @Test
    public void activateAndDeactivateWithRightPassword() {
        String password = "password";
        alarmSystem.activate(password);
        alarmSystem.deactivate(password);

        assertTrue(alarmSystem.getState() instanceof DeactivatedAlarmSystem);
    }

    @Test
    public void triggerAlarmAfterDeactivatingWithWrongPassword() {
        String password = "password";
        String wrongPassword = "wrongPassword";
        alarmSystem.activate(password);
        alarmSystem.deactivate(wrongPassword);

        assertTrue(alarmSystem.getState() instanceof AlarmingAlarmSystem);
    }

    @Test
    public void unableToTriggerAlarmManuallyFromDeactivatedState() {
        alarmSystem.alarm();

        assertTrue(alarmSystem.getState() instanceof DeactivatedAlarmSystem);
    }

    @Test
    public void triggerAlarmManuallyFromActivatedState() {
        alarmSystem.activate("password");
        alarmSystem.alarm();

        assertTrue(alarmSystem.getState() instanceof AlarmingAlarmSystem    );
    }
}