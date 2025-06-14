package logic.commands;

import logic.things.Bag;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;
import logic.things.Thing;

import java.util.Optional;

public class TakeCommand implements Command{
    private static final String NAME = "Take";
    private GamePlan gamePlan;

    public TakeCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    public String ExecuteCommand(String... parameters) {
        if (parameters.length == 0) {
            return "You must specify the name of the thing you want to take";
        }

        String thingName = parameters[0].toLowerCase();

        Room currentRoom = gamePlan.getCurrentRoom();

        Optional<Thing> thingCheck = currentRoom.getThingsInRoom().stream()
                .filter(t -> t.getName().equalsIgnoreCase(thingName))
                .findFirst();

        if (thingCheck.isEmpty()) {
            return "There is no such thing in this room";
        }

        Thing thing = thingCheck.get();

        if (!thing.isCarriable()) {
            return "You can't carry this thing";
        }

        if (!Bag.getInstance().addThing(thing)) {
            return "You can't carry more things, your bag is full";
        }

        currentRoom.removeThing(thing);

        return "You took the " + thing.getName() + " and placed it in your bag";
    }

    public String getName() {
        return NAME;
    }
}
