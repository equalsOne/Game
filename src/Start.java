import logic.gamelogic.Game;
import logic.gamelogic.IGame;
import uiText.UserTextInterface;

public class Start {
    public static void main(String[] args) {
        IGame currentIGame = new Game();

        UserTextInterface textInterface = new UserTextInterface(currentIGame);

        if(args.length == 0){
            textInterface.play();
        }
        else { textInterface.playFromFile(args[0]);
    }
}
}