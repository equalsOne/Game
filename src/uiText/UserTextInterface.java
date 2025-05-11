package uiText;

import logic.gamelogic.IGame;

import java.io.*;
import java.util.Scanner;

public class UserTextInterface {
    private IGame currentIGame;

    public UserTextInterface(IGame currentIGame) { this.currentIGame = currentIGame; }

    private String readString(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("> ");

        return scanner.nextLine();
    }

    public void play(){
        System.out.println(currentIGame.inceptionMessage());

        while(!currentIGame.isOver()){
            String message = readString();

            System.out.println(currentIGame.handleCommand(message));
        }

        System.out.println(currentIGame.endMessage());
    }

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
