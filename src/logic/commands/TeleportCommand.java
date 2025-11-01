package logic.commands;

import logic.gamelogic.Game;

/**
 * TeleportCommand implements the Command interface and handles the
 * teleportation action which immediately ends the game in failure.
 *
 * The command syntax is: teleport
 * Upon execution, the player is sent to a void dimension and dies,
 * ending the game abruptly.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class TeleportCommand implements Command{
    private static final String NAME = "Teleport";
    private Game game;

    /**
     * Constructs the TeleportCommand with the current game context.
     *
     * @param game the main game instance controlling game state
     */
    public TeleportCommand(Game game) {
        this.game = game;
    }

    /**
     * Executes the "teleport" command, ending the game with a death scenario.
     *
     * @param parameters ignored for this command
     * @return a message describing the player's demise
     */
    public String ExecuteCommand(String... parameters) {
        game.setOver(true);

        return "You touched the portal and got lost in a void dimension...\n" +
                "You are dead.\n" +
                "Don't repeat this mistake the next time!";
    }

    /**
     * Returns the command name used in the parser.
     *
     * @return the command keyword ("Teleport")
     */
    public String getName() {
        return NAME;
    }
}
