package logic.commands;

import java.util.*;

/*******************************************************************************
 * Class CommandsList manages a collection of all available in-game commands.
 *
 * It stores the commands in a map, where the key is the command name
 * (converted to lowercase for case-insensitive access), and the value is
 * the corresponding Command object.
 *
 * This class is used to register and retrieve commands dynamically,
 * supporting flexible command handling.
 *
 * Example usage:
 * - addCommand(new GoCommand(gamePlan));
 * - Command cmd = getCommand("go");
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class CommandsList {
    private final Map<String, Command> commandsList;

    /**
     * Constructs an empty list of commands using a HashMap.
     */
    public CommandsList() { commandsList = new HashMap<>(); }

    /**
     * Registers a new command in the list.
     *
     * @param command the Command object to add
     */
    public void addCommand(Command command)
    { commandsList.put(command.getName().toLowerCase(), command); }

    /**
     * Retrieves a command by its name (case-insensitive).
     *
     * @param name the name of the command
     * @return the corresponding Command object, or null if not found
     */
    public Command getCommand(String name)
    { return commandsList.get(name.toLowerCase()); }

    /**
     * Returns a set of all registered command names.
     *
     * @return a Set of command name strings
     */
    public Set<String> getCommandsNames()
    { return commandsList.keySet(); }
}
