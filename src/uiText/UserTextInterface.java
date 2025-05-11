package uiText;

import logic.gamelogic.Game;

import java.io.*;
import java.util.Scanner;

public class UserTextInterface {
    private Game currentGame;

    public UserTextInterface(Game currentGame) { this.currentGame = currentGame; }

    private String readString(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("> ");

        return scanner.nextLine();
    }

    public void play(){
        System.out.println(currentGame.inceptionMessage());

        while(!currentGame.isOver()){
            String message = readString();

            System.out.println(currentGame.handleCommand(message));
        }

        System.out.println(currentGame.endMessage());
    }

    public void playFromFile (String fileName) {
        try (
                BufferedReader reading = new BufferedReader(new FileReader(fileName));

                PrintWriter writing = new PrintWriter(new FileWriter("results.txt"))
        ) {
            System.out.println(currentGame.inceptionMessage());

            writing.println(currentGame.inceptionMessage());


            for (String string = reading.readLine();
                 string != null && !currentGame.isOver(); string = reading.readLine())
            {
                System.out.println("> " + string);

                writing.println("> " + string);

                String out = currentGame.handleCommand(string);

                System.out.println(out);

                writing.println(out);
            }

            System.out.println(currentGame.endMessage());

            writing.println(currentGame.endMessage());

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
