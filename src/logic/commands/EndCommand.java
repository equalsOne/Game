package logic.commands;

import logic.gamelogic.IGame;

public class EndCommand implements Command{
    private static final String NAME = "End";
    private IGame currentIGame;

    public EndCommand(IGame currentIGame) { this.currentIGame = currentIGame; }

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

    public String getName() {
        return NAME;
    }
}
