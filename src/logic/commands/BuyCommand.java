package logic.commands;

import logic.characters.Dwarf;
import logic.gamelogic.GamePlan;
import logic.things.Bag;
import logic.things.Sword;
import logic.things.Thing;

public class BuyCommand implements Command{
    private static final String name = "Buy";
    private GamePlan gamePlan;
    private Bag bag;

    public BuyCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
        this.bag = gamePlan.getBag();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String ExecuteCommand(String... parameters) {
        if (parameters.length == 0) {
            return "You need to specify what you want to buy";
        }
        else if(!gamePlan.getCurrentRoom().getName().equals("DwarfHut")){
            return "There is no dwarf here to buy anything from";
        }

        Dwarf dwarf = (Dwarf) gamePlan.getCurrentRoom().getCharactersInRoom().stream()
                .filter(c -> c instanceof Dwarf)
                .findFirst()
                .orElse(null);

        if(dwarf == null){
            return "Dwarf isn't present here";
        }

        String thingName = parameters[0].toLowerCase();

        if (!hasCoinsInBag()) {
            return "You don't have enough coins to buy anything";
        }

        Thing thingToBuy = getThingToBuy(thingName);

        if (thingToBuy == null) {
            return "This thing is not available for purchase";
        }

        if (buyThing(thingToBuy)) {
            dwarf.deleteGood(thingToBuy);

            return "You have bought the " + thingToBuy.getName();
        }
        else { return "Something went wrong"; }
    }

    private boolean hasCoinsInBag() {
        return bag.getThingsNamesInBag().stream()
                .anyMatch(name -> name.equalsIgnoreCase("coins"));
    }

    private Thing getThingToBuy(String thingName) {
        if (thingName.equalsIgnoreCase("sword")) {
            return new Sword();
        }
        return null;
    }

    private boolean buyThing(Thing thing) {
        if (hasCoinsInBag()) {
            bag.removeThing("coins");

            bag.addThing(thing);

            return true;
        }

        return false;
    }
}
