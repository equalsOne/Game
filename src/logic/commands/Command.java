package logic.commands;

/*******************************************************************************
 * The Command interface defines the structure for all in-game commands.
 * Each command must be able to execute an action based on optional parameters
 * and return a result string, as well as provide its identifying name.
 *
 * This interface is implemented by specific command classes such as
 * BuyCommand, AttackCommand, BribeCommand, etc.
 *
 * It enables the command pattern to encapsulate user actions in a modular way.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public interface Command {
    /**
     * Executes the command logic with the provided parameters.
     *
     * @param parameters a variable-length array of input strings passed by the user
     * @return the result of the command execution, typically a message to the player
     */
    String ExecuteCommand(String... parameters);

    /**
     * Returns the name (keyword) of the command used to invoke it from user input.
     *
     * @return the command name as a String
     */
    String getName();
}
