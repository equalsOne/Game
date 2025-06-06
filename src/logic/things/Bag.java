package logic.things;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
// singleton
public class Bag {
    private static Bag instance;
    private static final int CAPACITY = 5;
    private final List<Thing> thingsInBag;

    private Bag(){
        thingsInBag = new ArrayList<>();
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
        return thingsInBag.stream()
            .map(Thing::getName)
            .collect(Collectors.toList());
    }

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

    public boolean removeThing(Thing thing){
        return thingsInBag.remove(thing);
    }

    public boolean removeThing(String name){
        return thingsInBag.removeIf(t -> t.getName().equals(name));
    }

    public Thing getThingByName(String name) {
        return thingsInBag.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public boolean hasThing(String name) {
        return thingsInBag.stream().anyMatch(thing ->
                thing.getName().equalsIgnoreCase(name));
    }
}
