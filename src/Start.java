import logic.gamelogic.Game;
import logic.gamelogic.IGame;
import uiText.UserTextInterface;

public class Start {
    public static void main(String[] args) {
        IGame currentGame = new Game();

        UserTextInterface textInterface = new UserTextInterface(currentGame);

        if(args.length == 0){
            textInterface.play();
        }
        else { textInterface.playFromFile(args[0]);
    }
}
}