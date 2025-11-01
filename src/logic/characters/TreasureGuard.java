package logic.characters;

/*******************************************************************************
 * Class TreasureGuard extends the Character base, guards the treasure door
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class TreasureGuard extends Character{
    private int attemptsLeft = 2;
    private boolean riddleSolved = false;
    private boolean isBribed = false;

    /**
     *  Class constructor
     */
    public TreasureGuard() {
        super("TreasureGuard", "");
    }

    /**
     * Returns the amount of attempts which are left to solve the problem
     *
     * @return attemptsLeft
     */
    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    /**
     * Decreases the attempts to solve the problem
     *
     */
    public void decreaseAttempts() {
        if (attemptsLeft > 0) attemptsLeft--;
    }

    /**
     * Returns the boolean of whether the problem is solved
     *
     * @return riddleSolved
     */
    public boolean isRiddleSolved() {
        return riddleSolved;
    }

    /**
     * Sets the riddle as solved
     *
     * @param solved as solved or not (boolean)
     */
    public void setRiddleSolved(boolean solved) {
        this.riddleSolved = solved;
    }

    /**
     * Returns the boolean of whether the guard was bribed or no
     *
     * @return isBribed
     */
    public boolean isBribed() {
        return isBribed;
    }

    /**
     * Sets the bribed status
     *
     * @param bribed as bribed or not
     */
    public void setBribed(boolean bribed) {
        this.isBribed = bribed;

        if (bribed) { this.attemptsLeft = Integer.MAX_VALUE; }
    }

    /**
     * Returns the message for the player
     *
     * @return message
     */
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
