package logic.commands;

/**
 * PromptCommand implements the Command interface and provides the player
 * with a prompt containing a brief game goal and a list of available commands.
 *
 * The command syntax is: prompt
 * It displays instructions on unlocking the treasures and lists all commands
 * the player can use in the game.
 *
 * If parameters are provided, it returns an error message indicating incorrect usage.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class PromptCommand implements Command{
    private static final String NAME = "Prompt";
    private CommandsList commandsList;
    private static final String PROMPT = "You have to unlock the treasures, " +
            "hidden in the treasure room. " +
            "\nYou are going to need key for this. " +
            "In the game, you can use this commands: \n";

    /**
     * Constructs the PromptCommand with the list of all available commands.
     *
     * @param commandsList the CommandsList containing all commands' names
     */
    public PromptCommand(CommandsList commandsList) { this.commandsList = commandsList; }

    /**
     * Returns the command name used in the parser.
     *
     * @return the command keyword ("Prompt")
     */
    public String getName() { return NAME; }

    /**
     * Executes the "prompt" command, returning the game goal and a list
     * of all available commands.
     *
     * @param parameters should be empty; if parameters are present, returns an error message
     * @return the prompt message with commands list or error message for incorrect usage
     */
    public String ExecuteCommand(String... parameters) {
        return parameters.length != 0 ? "Sorry, this command doesn't exist. " +
                "\nMaybe you've meant 'Prompt', " +
                "which helps you to find out all the commands? " +
                "\nThen just type 'prompt'"
                : PROMPT + String.join("\n",
                commandsList.getCommandsNames());
    }
}
