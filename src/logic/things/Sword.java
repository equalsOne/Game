package logic.things;

/**
 * Represents a sword object in the game.
 * The sword is carriable and occupies 3 spaces in the bag.
 * It is used to defeat the spider.
 *
 * Author: Ihor Skosar
 * Version: LS 2024/2025, June 2025
 */
public class Sword extends Thing {
    /**
     * Constructs a Sword object with name "sword", size 3, and carriable.
     */
    public Sword(){
        super("sword", 3, true);
    }
}
