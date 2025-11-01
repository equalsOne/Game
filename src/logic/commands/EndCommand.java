package logic.commands;

import logic.gamelogic.IGame;

/*******************************************************************************
 * Class EndCommand implements the Command interface and represents the command
 * that ends the current game session.
 *
 * The command syntax is: end
 * Example: end
 *
 * When executed correctly (without additional parameters), this command sets
 * the game state to "over", which causes the game loop to terminate.
 * If the player tries to pass any arguments, it returns an error message.
 *
 * This command allows the player to manually finish the game at any time.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class EndCommand implements Command{
    private static final String NAME = "End";
    private IGame currentIGame;

    /**
     * Constructs a new EndCommand with the given game instance.
     *
     * @param currentIGame the current game interface for controlling game flow
     */
    public EndCommand(IGame currentIGame) { this.currentIGame = currentIGame; }

    /**
     * Executes the end command, which terminates the game.
     *
     * @param parameters should be empty; any additional input will trigger an error
     * @return result message of the command execution
     */
    public String ExecuteCommand(String... parameters) {
        if(parameters.length > 0){
            return "Sorry, this command doesn't exist. " +
                    "Maybe you've meant 'End', which ends the game?";
        }
        else
        {
            currentIGame.setOver(true);

            return "The game has been terminated";
        }
    }

    /**
     * Returns the name of the command.
     *
     * @return command name ("End")
     */
    public String getName() {
        return NAME;
    }
}
