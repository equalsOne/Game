package logic.things;

/**
 * Represents an item or object in the game world.
 * Each Thing has a name, occupies a certain amount of space,
 * and can be either carriable or not.
 *
 * Author: Ihor Skosar
 * Version: LS 2024/2025, June 2025
 */
public class Thing {
    private String name;
    private int space;
    private boolean isCarriable;

    /**
     * Constructs a Thing with the specified name, space it occupies,
     * and whether it can be carried.
     *
     * @param name the name of the thing
     * @param space the space the thing occupies in the bag
     * @param isCarriable whether the thing can be carried by the player
     */
    public Thing(String name, int space, boolean isCarriable){
        this.name = name;
        this.space = space;
        this.isCarriable = isCarriable;
    }

    /**
     * Constructs a Thing with the specified name and carriable status.
     * Space is not set and may default to 0.
     *
     * @param name the name of the thing
     * @param isCarriable whether the thing can be carried by the player
     */
    public Thing(String name, boolean isCarriable){
        this.name = name;
        this.isCarriable = isCarriable;
    }

    /**
     * Sets the name of the thing.
     * @param name new name of the thing
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the name of the thing.
     * @return name of the thing
     */
    public String getName() { return name; }

    /**
     * Returns the space occupied by the thing.
     * @return space occupied by the thing
     */
    public int getSpace() { return space; }

    /**
     * Returns whether the thing is carriable.
     * @return true if the thing can be carried, false otherwise
     */
    public boolean isCarriable() { return isCarriable; }
}
