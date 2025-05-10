import logic.*;
import uiText.UserTextInterface;

public class Start {
    public static void main(String[] args) {
        Game currentGame = new CurrentGame();

        UserTextInterface textInterface = new UserTextInterface(currentGame);

        if(args.length == 0){
            textInterface.play();
        }
        else { textInterface.playFromFile(args[0]);
    }
}
}