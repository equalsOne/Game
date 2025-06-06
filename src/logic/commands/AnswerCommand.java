package logic.commands;

import logic.characters.TreasureGuard;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;

public class AnswerCommand implements Command{
    private static final String NAME = "Answer";
    private GamePlan gamePlan;

    public AnswerCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    public String ExecuteCommand(String... parameters) {
        Room currentRoom = gamePlan.getCurrentRoom();

        TreasureGuard guard = (TreasureGuard) currentRoom.getCharactersInRoom().stream()
                .filter(c -> c instanceof TreasureGuard)
                .findFirst()
                .orElse(null);

        if (guard == null) {
            return "There's no one here to answer";
        }

        if (parameters.length == 0) {
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

        if (answer.equals("echo")) {
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

    public String getName() {
        return NAME;
    }
}
