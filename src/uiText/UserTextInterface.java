package uiText;

import logic.gamelogic.IGame;

import java.io.*;
import java.util.Scanner;

/**
 * Handles user interaction for the game.
 *
 * Provides two ways to play:
 * - interactively via console input
 * - reading commands from a file and logging output
 *
 * Author: Ihor Skosar
 * Version: LS 2024/2025, June 2025
 */
public class UserTextInterface {
    private IGame currentIGame;

    /**
     * Constructs the text interface with the given game instance.
     *
     * @param currentIGame the game instance to interact with
     */
    public UserTextInterface(IGame currentIGame)
    { this.currentIGame = currentIGame; }

    /**
     * Reads a line of user input from the console.
     *
     * @return the string entered by the user
     */
    private String readString(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("> ");

        return scanner.nextLine();
    }

    /**
     * Runs the game interactively with user input from the console.
     * Prints messages to standard output.
     */
    public void play(){
        System.out.println(currentIGame.inceptionMessage());

        while(!currentIGame.isOver()){
            String message = readString();

            System.out.println(currentIGame.handleCommand(message));
        }

        System.out.println(currentIGame.endMessage());
    }

    /**
     * Plays the game by reading commands from a file.
     * Outputs both commands and game responses to console and logs them to "results.txt".
     *
     * @param fileName the name of the input file containing commands
     */
    public void playFromFile (String fileName) {
        try (
                BufferedReader reading = new BufferedReader(new FileReader(fileName));

                PrintWriter writing = new PrintWriter(new FileWriter("results.txt"))
        ) {
            System.out.println(currentIGame.inceptionMessage());

            writing.println(currentIGame.inceptionMessage());


            for (String string = reading.readLine();
                 string != null && !currentIGame.isOver(); string = reading.readLine())
            {
                System.out.println("> " + string);

                writing.println("> " + string);

                String out = currentIGame.handleCommand(string);

                System.out.println(out);

                writing.println(out);
            }

            System.out.println(currentIGame.endMessage());

            writing.println(currentIGame.endMessage());

        } catch (FileNotFoundException e) {
            File file = new File(fileName);

            System.out.println("The file wasn't found!\nThe path was: "
                    + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Playing from file was terminated, the problem was: "
                    + e.getMessage());
        }
    }
}
