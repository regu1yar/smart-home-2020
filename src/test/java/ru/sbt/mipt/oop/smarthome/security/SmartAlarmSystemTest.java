package ru.sbt.mipt.oop.smarthome.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class SmartAlarmSystemTest {
    private final String defaultCode = "defaultCode";
    private AlarmSystem alarmSystem;

    @Before
    public void setUp() {
        alarmSystem = new SmartAlarmSystem(defaultCode);
    }

    @Test
    public void initiallyDeactivated() {
        assertTrue(alarmSystem.getState() instanceof DeactivatedAlarmSystem);
    }

    @Test
    public void activatedAfterActivateWithCode() {
        alarmSystem.activate("code");

        assertTrue(alarmSystem.getState() instanceof ActivatedAlarmSystem);
    }

    @Test
    public void activateAndDeactivateWithRightCode() {
        String code = "code";
        alarmSystem.activate(code);
        alarmSystem.deactivate(code);

        assertTrue(alarmSystem.getState() instanceof DeactivatedAlarmSystem);
    }

    @Test
    public void triggerAlarmAfterDeactivatingWithWrongCode() {
        String code = "code";
        String wrongCode = "wrongCode";
        alarmSystem.activate(code);
        alarmSystem.deactivate(wrongCode);

        assertTrue(alarmSystem.getState() instanceof AlarmingAlarmSystem);
    }

    @Test
    public void triggerAlarmManuallyFromDeactivatedState() {
        alarmSystem.alarm();

        assertTrue(alarmSystem.getState() instanceof AlarmingAlarmSystem);
    }

    @Test
    public void triggerAlarmManuallyFromActivatedState() {
        String code = "code";
        alarmSystem.activate(code);
        alarmSystem.alarm();

        assertTrue(alarmSystem.getState() instanceof AlarmingAlarmSystem);
    }

    @Test
    public void triggerAlarmManuallyFromActivatedStateAndDeactivateThen() {
        String code = "code";
        alarmSystem.activate(code);
        alarmSystem.alarm();
        alarmSystem.deactivate(code);

        assertTrue(alarmSystem.getState() instanceof DeactivatedAlarmSystem);
    }

    @Test
    public void activateAlarmManuallyFromDeactivatedState() {
        String code = "code";
        alarmSystem.activate(code);

        assertTrue(alarmSystem.getState() instanceof ActivatedAlarmSystem);
    }

    @Test
    public void triggerAlarmManuallyFromActivatedStateAndFailToDeactivateThenWithWrongCode() {
        String code = "code";
        String wrongCode = "wrongCode";
        alarmSystem.activate(code);
        alarmSystem.alarm();
        alarmSystem.deactivate(wrongCode);

        assertTrue(alarmSystem.getState() instanceof AlarmingAlarmSystem);
    }

    @Test
    public void triggerAlarmWithAnotherCodeFromActivatedStateAndFailToDeactivateThenWithIt() {
        String code = "code";
        String wrongCode = "wrongCode";
        alarmSystem.activate(code);
        alarmSystem.alarm(wrongCode);
        alarmSystem.deactivate(wrongCode);

        assertTrue(alarmSystem.getState() instanceof AlarmingAlarmSystem);
    }

    @Test
    public void triggerAlarmWithManuallyFromDeactivatedStateDeactivateWithDefaultCode() {
        alarmSystem.alarm();
        alarmSystem.deactivate(defaultCode);

        assertTrue(alarmSystem.getState() instanceof DeactivatedAlarmSystem);
    }
}