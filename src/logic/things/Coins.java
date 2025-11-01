package logic.things;

/**
 * Represents coins as a carriable thing in the game.
 * Coins take up 3 units of space in the bag.
 *
 * Author: Ihor Skosar
 * Version: LS 2024/2025, June 2025
 */
public class Coins extends Thing {
    /**
     * Constructs Coins with name "coins", space 3, and carriable = true.
     */
    public Coins(){
        super("coins", 3, true);
    }
}
