package logic.things;

/**
 * Represents a portal object in the game.
 * The portal is not carriable by the player.
 *
 * Author: Ihor Skosar
 * Version: LS 2024/2025, June 2025
 */
public class Portal extends Thing{
    /**
     * Constructs a Portal with the name "portal" and sets it as non-carriable.
     */
    public Portal() { super("portal", false); }
}
