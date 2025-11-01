package logic.commands;

import logic.characters.TreasureGuard;
import logic.gamelogic.*;
import logic.things.Bag;

/**
 * OpenCommand implements the Command interface and handles the action
 * of opening the treasure in the Treasure Room.
 *
 * The command syntax is: open
 * It only works if the player is in the Treasure Room, has the key,
 * and the Treasure Guard's riddle has been solved.
 *
 * If these conditions are met, the game ends with a victory message.
 * Otherwise, appropriate error messages are shown.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class OpenCommand implements Command{
    private static final String NAME = "Open";
    private GamePlan gamePlan;
    private Game game;

    /**
     * Constructs the OpenCommand with the current game plan and game context.
     *
     * @param gamePlan the active game plan that holds room and player state
     * @param game the main game instance controlling game state
     */
    public OpenCommand(GamePlan gamePlan, Game game) {
        this.gamePlan = gamePlan;
        this.game = game;
    }

    /**
     * Executes the "open" command, attempting to open the treasure door.
     * Checks for player location, possession of the key, and if the treasure
     * guard's riddle is solved.
     *
     * @param parameters should be empty; if parameters are provided, an error is returned
     * @return a message indicating success and game completion or reason for failure
     */
    public String ExecuteCommand(String... parameters) {
        if(parameters.length > 0){
            return "This command works only if you have the key " +
                    "and you're in the Treasure Room!" +
                    "\nIf you already do have and are here, just type 'open'";
        }

        Room currentRoom = gamePlan.getCurrentRoom();

        if (!currentRoom.getName().equalsIgnoreCase("TreasureRoom")) {
            return "You need to be in the Treasure Room to open the treasure.";
        }

        if (!Bag.getInstance().hasThing("Key")) {
            return "The treasure is locked. You need a key to open it";
        }

        TreasureGuard guard =
                (TreasureGuard) currentRoom.getCharactersInRoom().stream()
                .filter(c -> c instanceof TreasureGuard)
                .findFirst()
                .orElse(null);

        if (guard != null && !guard.isRiddleSolved()) {
            return "The Treasure Guard blocks your way: " +
                    "\nEven with the key, you shall not pass without " +
                    "solving my riddle!";
        }

        game.setOver(true);

        return "You used the key and opened the treasure door!" +
                "\nCongratulations! You’ve completed the game!";
    }

    /**
     * Returns the command name used in the parser.
     *
     * @return the command keyword ("Open")
     */
    public String getName() {
        return NAME;
    }
}
