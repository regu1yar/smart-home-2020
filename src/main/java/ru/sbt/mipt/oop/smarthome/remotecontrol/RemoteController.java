package ru.sbt.mipt.oop.smarthome.remotecontrol;

import rc.RemoteControl;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RemoteController implements RemoteControl {
    private final String id;
    private Map<String, Command> buttonsToCommands = new HashMap<>();

    private static final Set<String> BUTTON_CODES = Set.of("A", "B", "C", "D", "1", "2", "3", "4");

    public RemoteController(String id) {
        this.id = id;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (id.equals(rcId) && buttonsToCommands.containsKey(buttonCode)) {
            buttonsToCommands.get(buttonCode).execute();
        }
    }

    public void setCommand(String buttonCode, Command command) {
        if (BUTTON_CODES.contains(buttonCode)) {
            buttonsToCommands.put(buttonCode, command);
        }
    }
}
