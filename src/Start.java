import logic.gamelogic.Game;
import logic.gamelogic.IGame;
import uiText.UserTextInterface;

/**
 * The entry point for the game application.
 *
 * If no command-line arguments are provided, starts the game interactively in console mode.
 * If a file name is provided as the first argument, reads commands from that file and plays the game automatically,
 * logging the input and output.
 *
 * Author: Ihor Skosar
 * Version: LS 2024/2025, June 2025
 */
public class Start {
    /**
     * Main method to start the game.
     *
     * @param args optional command-line arguments; if present, args[0] is used as input file for commands
     */
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