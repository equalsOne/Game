package logic.commands;

import logic.gamelogic.Game;

public class TeleportCommand implements Command{
    private static final String NAME = "Teleport";
    private Game game;

    public TeleportCommand(Game game) {
        this.game = game;
    }

    public String ExecuteCommand(String... parameters) {
        game.setOver(true);

        return "You touched the portal and got lost in a void dimension...\n" +
                "You are dead.\n" +
                "Don't repeat this mistake the next time!";
    }

    public String getName() {
        return NAME;
    }
}
