package logic.commands;

import java.util.*;

public class CommandsList {
    private final Map<String, Command> COMMANDS_LIST;

    public CommandsList() { COMMANDS_LIST = new HashMap<>(); }

    public void addCommand(Command command)
    { COMMANDS_LIST.put(command.getName().toLowerCase(), command); }

    public Command getCommand(String name)
    { return COMMANDS_LIST.get(name.toLowerCase()); }

    public Set<String> getCommandsNames()
    { return COMMANDS_LIST.keySet(); }
}
