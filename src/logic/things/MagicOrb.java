package logic.things;

/**
 * Represents a magic orb item that can be carried by the player.
 * The orb takes up 1 unit of space in the bag.
 *
 * Author: Ihor Skosar
 * Version: LS 2024/2025, June 2025
 */
public class MagicOrb extends Thing{
    /**
     * Constructs a MagicOrb with name "orb", space 1, and carriable = true.
     */
    public MagicOrb() { super("orb", 1, true); }
}
