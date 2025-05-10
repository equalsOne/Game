package logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Bag {
    private final int capacity = 5;
    private final List<ThingBase> thingsInBag;

    public Bag(){
        this.thingsInBag = new ArrayList<>();
    }

    public int getCapacity() { return capacity; }

    public Collection<String> getThingsNamesInBag()
    {
        return thingsInBag.stream()
            .map(ThingBase::getName)
            .collect(Collectors.toList());
    }

    public boolean addThing(ThingBase thing){
        if(thingsInBag.size() < capacity &&
                (thingsInBag.size() + thing.getSpace()) <= capacity
                && thing.isCarriable())
        {
            return thingsInBag.add(thing);
        }
        else { return false; }
    }

    public boolean removeThing(ThingBase thing){
        return thingsInBag.remove(thing);
    }

    public boolean removeThing(String name){
        return thingsInBag.removeIf(t -> t.getName().equals(name));
    }

    public ThingBase getThingByName(String name) {
        return thingsInBag.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
