package logic.things;

/**
 * Represents a key item that can be carried by the player.
 * The key takes up 1 unit of space in the bag.
 *
 * Author: Ihor Skosar
 * Version: LS 2024/2025, June 2025
 */
public class Key extends Thing{
    /**
     * Constructs a Key with name "key", space 1, and carriable = true.
     */
    public Key(){
        super("key", 1, true);
    }
}
