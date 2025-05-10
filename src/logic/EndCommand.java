package logic;

public class EndCommand implements Command{

    private static final String name = "End";
    private Game currentGame;

    public EndCommand(Game currentGame) { this.currentGame = currentGame; }

    @Override
    public String doCommand(String... parameters) {
        if(parameters.length > 0){
            return "Sorry, this command doesn't exist. " +
                    "Maybe you've meant 'End', which ends the game?";
        }
        else
        {
            currentGame.setOver(true);

            return "The game has been terminated";
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
