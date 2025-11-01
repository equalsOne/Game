package logic.commands;

import logic.things.Bag;
import logic.gamelogic.GamePlan;
import logic.things.Thing;

import java.util.Collection;
import java.util.stream.Collectors;

/*******************************************************************************
 * Class DisposeCommand implements the Command interface and represents the
 * command that allows the player to remove an item from their bag and place
 * it back in the current room.
 *
 * The command syntax is: dispose <thingName>
 * Example: dispose sword
 *
 * If the specified item exists in the bag, it is removed and placed in the room.
 * Otherwise, the command responds with an error message and shows the contents
 * of the bag if available.
 *
 * This can be used to free space in the player's inventory.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class DisposeCommand implements Command{
    private static final String NAME = "Dispose";
    private GamePlan gamePlan;

    /**
     * Constructs a new DisposeCommand.
     *
     * @param gamePlan the current game plan to access the game state
     */
    public DisposeCommand(GamePlan gamePlan){
        this.gamePlan = gamePlan;
    }

    /**
     * Returns the name of the command.
     *
     * @return command name ("Dispose")
     */
    public String getName() { return NAME; }

    /**
     * Executes the dispose command, which removes a thing from the player's bag
     * and places it in the current room.
     *
     * @param parameters expects one parameter: the name of the thing to dispose
     * @return result message of the command execution
     */
    public String ExecuteCommand(String... parameters){
        if(parameters.length != 1){
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
