package logic.commands;

import logic.characters.Dwarf;
import logic.gamelogic.GamePlan;
import logic.things.Bag;
import logic.things.Sword;
import logic.things.Thing;

/*******************************************************************************
 * Class BuyCommand implements the Command interface, representing
 * the command used to buy items from the dwarf character.
 *
 * Currently, the only available item for purchase is the sword,
 * and it requires 3 coins. The player must be in the "DwarfHut" room,
 * and the dwarf must be present to execute the transaction.
 *
 * Usage: 'buy sword'
 *
 * This command checks if the player has coins and is in the correct
 * location before allowing the purchase.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class BuyCommand implements Command{
    private static final String NAME = "Buy";
    private GamePlan gamePlan;

    /**
     * Class constructor
     *
     * @param gamePlan the current game plan used to access the room and characters
     */
    public BuyCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    /**
     * Executes the buy command.
     * The player can buy a sword from the dwarf if they are in the correct room
     * and have the required coins in their bag.
     *
     * @param parameters expects a single word "sword"
     * @return a message indicating the result of the attempt to buy
     */
    public String ExecuteCommand(String... parameters) {
        if (parameters.length != 1) {
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

    /**
     * Checks if the player has coins in their bag
     *
     * @return true if coins are found, false otherwise
     */
    private boolean hasCoinsInBag() {
        return Bag.getInstance().getThingsNamesInBag().stream()
                .anyMatch(name -> name.equalsIgnoreCase("coins"));
    }

    /**
     * Gets the item the player wants to buy, if available
     *
     * @param thingName the name of the item
     * @return a new instance of the item or null if not available
     */
    private Thing getThingToBuy(String thingName) {
        if (thingName.equalsIgnoreCase("sword")) {
            return new Sword();
        }
        return null;
    }

    /**
     * Performs the transaction: removes coins and adds the item to the bag
     *
     * @param thing the item to purchase
     * @return true if purchase was successful, false otherwise
     */
    private boolean buyThing(Thing thing) {
        if (hasCoinsInBag()) {
            Bag.getInstance().removeThing("coins");

            Bag.getInstance().addThing(thing);

            return true;
        }

        return false;
    }

    /**
     * Returns the name of the command
     *
     * @return NAME - the command keyword ("buy")
     */
    public String getName() {
        return NAME;
    }
}
