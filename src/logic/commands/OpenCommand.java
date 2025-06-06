package logic.commands;

import logic.characters.TreasureGuard;
import logic.gamelogic.*;
import logic.things.Bag;

public class OpenCommand implements Command{
    private static final String NAME = "open";
    private GamePlan gamePlan;
    private Game game;

    public OpenCommand(GamePlan gamePlan, Game game) {
        this.gamePlan = gamePlan;
        this.game = game;
    }

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

    public String getName() {
        return NAME;
    }
}
