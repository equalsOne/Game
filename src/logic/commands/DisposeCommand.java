package logic.commands;

import logic.things.Bag;
import logic.gamelogic.GamePlan;
import logic.things.Thing;

import java.util.Collection;
import java.util.stream.Collectors;

public class DisposeCommand implements Command{
    private static final String NAME = "Dispose";
    private GamePlan gamePlan;

    public DisposeCommand(GamePlan gamePlan){
        this.gamePlan = gamePlan;
    }

    public String getName() { return NAME; }

    public String ExecuteCommand(String... parameters){
        if(parameters.length == 0){
            return "Write the name of the thing you want to throw out of your bag";
        }

        String thingName = parameters[0];

        Thing thing = Bag.getInstance().getThingByName(thingName);

        if(thing == null) {
            String items = Bag.getInstance().getThingsNamesInBag().stream()
                    .collect(Collectors.joining(", "));

            return items.isBlank() ?
                    "You don't have that in your bag. Your bag is empty!" :
                    "You don't have that in your bag. " +
                            "Things in your bag are: " + items;
        }

        Bag.getInstance().removeThing(thingName);

        gamePlan.getCurrentRoom().addToThingsInRoom(thing);

        Collection<String> thingsLeft = Bag.getInstance().getThingsNamesInBag();

        if (thingsLeft.isEmpty()) {
            return "It was thrown out! Your bag is now empty";
        }
        else {
            return "It was thrown out! Things in your bag right now: " +
                    String.join(" ", thingsLeft);
        }
    }
}
