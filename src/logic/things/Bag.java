package logic.things;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Singleton class representing the player's bag.
 * The bag holds a limited number of things (items) that the player can carry.
 * The capacity is measured in space units, and only carriable items can be added.
 *
 * Author: Ihor Skosar
 * Version: LS 2024/2025, June 2025
 */
public class Bag {
    private static Bag instance;
    private static final int CAPACITY = 5;
    private final List<Thing> thingsInBag;

    /**
     * Private constructor to enforce Singleton pattern.
     */
    private Bag(){
        thingsInBag = new ArrayList<>();
    }

    /**
     * Returns the single instance of the bag.
     * If it does not exist, creates it.
     *
     * @return the Bag instance
     */
    public static Bag getInstance(){
        if(instance == null){
            instance = new Bag();
        }

        return instance;
    }

    /**
     * Returns the names of all things currently in the bag.
     *
     * @return a collection of strings representing the names of things in the bag
     */
    public Collection<String> getThingsNamesInBag()
    {
        return thingsInBag.stream()
            .map(Thing::getName)
            .collect(Collectors.toList());
    }

    /**
     * Attempts to add a thing to the bag.
     * Checks if there is enough capacity and if the thing is carriable.
     *
     * @param thing the Thing to add
     * @return true if the thing was successfully added, false otherwise
     */
    public boolean addThing(Thing thing){
        if(thingsInBag.stream().mapToInt(Thing::getSpace).sum() < CAPACITY &&
                (thingsInBag.stream().mapToInt(Thing::getSpace).sum() +
                        thing.getSpace()) <= CAPACITY
                && thing.isCarriable())
        {
            return thingsInBag.add(thing);
        }
        else { return false; }
    }

    /**
     * Removes a specific thing from the bag.
     *
     * @param thing the Thing to remove
     * @return true if the thing was removed, false otherwise
     */
    public boolean removeThing(Thing thing){
        return thingsInBag.remove(thing);
    }

    /**
     * Removes a thing by its name from the bag.
     *
     * @param name the name of the thing to remove
     * @return true if a thing with the name was removed, false otherwise
     */
    public boolean removeThing(String name){
        return thingsInBag.removeIf(t -> t.getName().equals(name));
    }


    /**
     * Finds and returns a thing in the bag by its name.
     *
     * @param name the name of the thing to find
     * @return the Thing if found, or null if not found
     */
    public Thing getThingByName(String name) {
        return thingsInBag.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks if the bag contains a thing with the specified name.
     *
     * @param name the name of the thing to check for
     * @return true if the thing is in the bag, false otherwise
     */
    public boolean hasThing(String name) {
        return thingsInBag.stream().anyMatch(thing ->
                thing.getName().equalsIgnoreCase(name));
    }
}
