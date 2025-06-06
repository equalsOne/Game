package logic.characters;

public class TreasureGuard extends Character{
    private int attemptsLeft = 2;
    private boolean riddleSolved = false;
    private boolean isBribed = false;

    public TreasureGuard() {
        super("TreasureGuard", "");
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public void decreaseAttempts() {
        if (attemptsLeft > 0) attemptsLeft--;
    }

    public boolean isRiddleSolved() {
        return riddleSolved;
    }

    public void setRiddleSolved(boolean solved) {
        this.riddleSolved = solved;
    }

    public boolean isBribed() {
        return isBribed;
    }

    public void setBribed(boolean bribed) {
        this.isBribed = bribed;

        if (bribed) { this.attemptsLeft = Integer.MAX_VALUE; }
    }

    public String getMessageForPlayer() {
        if (riddleSolved) {
            return "You already solved my riddle. Go ahead";
        }

        String message = "TreasureGuard: 'Good job! I respect you for what you've done, " +
                "but the key alone isn't enough to open the door.\n" +
                "Solve my riddle, and I'll pass you:\n" +
                "\"I speak without a mouth and hear without ears. " +
                "I have no body, but I come alive with wind.\"\n" +
                "What am I: an echo, a wind or a stream of consciousness?";

        if (isBribed) {
            message += "\nSince you brought me a magical orb, " +
                    "you may try as many times as you need'";
        }
        else {
            message += "\nYou have only 2 attempts, so don't fail!'";
        }

        return message;
    }
}
