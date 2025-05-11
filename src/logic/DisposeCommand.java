package logic;

import java.util.Collection;

public class DisposeCommand implements Command{
    private static final String name = "Dispose";
    private GamePlan gamePlan;
    private Bag bag;

    public DisposeCommand(GamePlan gamePlan){
        this.gamePlan = gamePlan;
        this.bag = gamePlan.getBag();
    }

    public String getName() { return name; }

    public String ExecuteCommand(String... parameters){
        if(parameters.length == 0){
            return "Write the name of the thing you want to throw out of your bag";
        }

        String thingName = parameters[0];

        Thing thing = bag.getThingByName(thingName);

        if(thing == null) { return "You don't have that in your bag"; }

        bag.removeThing(thingName);

        gamePlan.getCurrentRoom().addToThingsInRoom(thing);

        Collection<String> thingsLeft = bag.getThingsNamesInBag();

        if (thingsLeft.isEmpty()) {
            return "It was thrown out! Your bag is now empty";
        }
        else {
            return "It was thrown out! Things in your bag right now: " +
                    String.join(" ", thingsLeft);
        }

    }
}
