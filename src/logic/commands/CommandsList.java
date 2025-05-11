package logic.commands;

import java.util.*;

public class CommandsList {
    private final Map<String, Command> commandsList;

    public CommandsList() { commandsList = new HashMap<>(); }

    public void addCommand(Command command)
    { commandsList.put(command.getName().toLowerCase(), command); }

    public Command getCommand(String name)
    { return commandsList.get(name.toLowerCase()); }

    public Set<String> getCommandsNames()
    { return commandsList.keySet(); }
}
