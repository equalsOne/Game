package logic.things;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
// singleton
public class Bag {
    private static Bag instance;
    private final int CAPACITY = 5;
    private final List<Thing> THINGS_IN_BAG;

    private Bag(){
        THINGS_IN_BAG = new ArrayList<>();
    }

    public static Bag getInstance(){
        if(instance == null){
            instance = new Bag();
        }

        return instance;
    }

    public int getCapacity() { return CAPACITY; }

    public Collection<String> getThingsNamesInBag()
    {
        return THINGS_IN_BAG.stream()
            .map(Thing::getName)
            .collect(Collectors.toList());
    }

    public boolean addThing(Thing thing){
        if(THINGS_IN_BAG.stream().mapToInt(Thing::getSpace).sum() < CAPACITY &&
                (THINGS_IN_BAG.stream().mapToInt(Thing::getSpace).sum() +
                        thing.getSpace()) <= CAPACITY
                && thing.isCarriable())
        {
            return THINGS_IN_BAG.add(thing);
        }
        else { return false; }
    }

    public boolean removeThing(Thing thing){
        return THINGS_IN_BAG.remove(thing);
    }

    public boolean removeThing(String name){
        return THINGS_IN_BAG.removeIf(t -> t.getName().equals(name));
    }

    public Thing getThingByName(String name) {
        return THINGS_IN_BAG.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public boolean hasThing(String name) {
        return THINGS_IN_BAG.stream().anyMatch(thing ->
                thing.getName().equalsIgnoreCase(name));
    }
}
