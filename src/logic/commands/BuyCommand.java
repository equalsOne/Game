package logic.commands;

import logic.characters.Dwarf;
import logic.gamelogic.GamePlan;
import logic.things.Bag;
import logic.things.Sword;
import logic.things.Thing;

public class BuyCommand implements Command{
    private static final String NAME = "Buy";
    private GamePlan gamePlan;

    public BuyCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    public String ExecuteCommand(String... parameters) {
        if (parameters.length == 0) {
            return "You can only buy sword. If you want to, type 'buy sword'";
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
            return "You can only buy sword. If you want to, type 'buy sword'";
        }

        if (buyThing(thingToBuy)) {
            dwarf.deleteGood(thingToBuy);

            return "You have bought the " + thingToBuy.getName() + " for 3 coins";
        }
        else { return "Something went wrong"; }
    }

    private boolean hasCoinsInBag() {
        return Bag.getInstance().getThingsNamesInBag().stream()
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
            Bag.getInstance().removeThing("coins");

            Bag.getInstance().addThing(thing);

            return true;
        }

        return false;
    }

    public String getName() {
        return NAME;
    }
}
