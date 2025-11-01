package logic.commands;

import logic.characters.TreasureGuard;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;

/*******************************************************************************
 * Class AnswerCommand implements the Command interface, the command for answering treasure guard's questions
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class AnswerCommand implements Command{
    private static final String NAME = "Answer";
    private GamePlan gamePlan;

    /**
     *  Class constructor
     *
     * @param gamePlan as current game plan
     */
    public AnswerCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    /**
     * Returns the result string after executing the answer command
     *
     * @return different strings
     */
    public String ExecuteCommand(String... parameters) {
        Room currentRoom = gamePlan.getCurrentRoom();

        TreasureGuard guard = (TreasureGuard) currentRoom.getCharactersInRoom().stream()
                .filter(c -> c instanceof TreasureGuard)
                .findFirst()
                .orElse(null);

        if (guard == null) {
            return "There's no one here to answer";
        }

        if (parameters.length != 1) {
            return "Answer what? Type the single word you think is correct";
        }

        if (guard.isRiddleSolved()) {
            return "You've already solved the riddle";
        }

        if (!guard.isBribed() && guard.getAttemptsLeft() <= 0) {
            return "TreasureGuard: 'I'm disappointed – you had your chances. " +
                    "Bring me something magical if you want another shot'" +
                    "\n\nThe treasure guard now wants you " +
                    "to find the magic orb for him. " +
                    "Look for it!";
        }

        String answer = parameters[0].toLowerCase();

        if (answer.equalsIgnoreCase("echo")) {
            guard.setRiddleSolved(true);

            return "Correct! You may now proceed to open the treasure";
        }
        else {
            if (!guard.isBribed()) {
                guard.decreaseAttempts();

                int remaining = guard.getAttemptsLeft();

                if (remaining > 0) {
                    return "No! You have " + remaining + " attempt(s) left";
                }
                else {
                    return "TreasureGuard: 'I'm disappointed – you may fight well, " +
                            "but you're not really smart. " +
                            "\nBring me something magical " +
                            "if you want another shot' " +
                            "\n\nThe treasure guard now wants you " +
                            "to find the magic orb for him. " +
                            "Look for it!";
                }
            }
            else {
                return "No, that's not correct. Try again";
            }
        }
    }

    /**
     * Returns the command's name
     *
     * @return NAME
     */
    public String getName() {
        return NAME;
    }
}
