package logic.gamelogic;

/**
 * Interface IGame defines the basic methods
 * required to run and control the game lifecycle,
 * handle user commands, and access the game state.
 *
 * @author Ihor Skosar
 * @version LS 2024/2025, June 2025
 */
public interface IGame {
    /**
     * Returns the introductory message displayed at the start of the game.
     *
     * @return the inception message as a String
     */
    String inceptionMessage();

    /**
     * Returns the closing message displayed when the game ends.
     *
     * @return the end message as a String
     */
    String endMessage();

    /**
     * Indicates whether the game is over.
     *
     * @return true if the game has ended, false otherwise
     */
    boolean isOver();

    /**
     * Processes a command input by the user.
     *
     * @param command the command string typed by the player
     * @return the response string after executing the command
     */
    String handleCommand(String command);


    /**
     * Returns the current GamePlan instance, which represents the game state.
     *
     * @return the GamePlan object
     */
    GamePlan getGamePlan();

    /**
     * Sets the flag indicating whether the game is over.
     *
     * @param isOver true to end the game, false to continue
     */
    void setOver(boolean isOver);
}
