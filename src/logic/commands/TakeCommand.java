package logic.commands;

import logic.things.Bag;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;
import logic.things.Thing;

import java.util.Optional;

/**
 * TakeCommand implements the Command interface and allows the player
 * to pick up a carriable object from the current room and place it into the bag.
 *
 * The command syntax is: take [thingName]
 * Example: take sword
 *
 * The command checks if the specified object exists in the room,
 * if it is carriable, and if there is enough space in the bag.
 * Appropriate messages are returned based on these checks.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class TakeCommand implements Command{
    private static final String NAME = "Take";
    private GamePlan gamePlan;

    /**
     * Constructs the TakeCommand with the current game plan context.
     *
     * @param gamePlan the active game plan that holds room and player state
     */
    public TakeCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    /**
     * Executes the "take" command to pick up an item from the current room
     * and add it to the player's bag if possible.
     *
     * @param parameters should contain exactly one argument: the name of the item to take
     * @return a message indicating success or the reason why the item cannot be taken
     */
    public String ExecuteCommand(String... parameters) {
        if (parameters.length != 1) {
            return "You must specify the name of the thing you want to take";
        }

        String thingName = parameters[0].toLowerCase();

        Room currentRoom = gamePlan.getCurrentRoom();

        Thing thingCheck = currentRoom.getThingsInRoom().stream()
                .filter(t -> t.getName().equalsIgnoreCase(thingName))
                .findFirst().orElse(null);

        if (thingCheck == null) {
            return "There is no such thing in this room";
        }

        if (!thingCheck.isCarriable()) {
            return "You can't carry this thing";
        }

        if (!Bag.getInstance().addThing(thingCheck)) {
            return "You can't carry more things, your bag is full";
        }

        currentRoom.removeThing(thingCheck);

        return "You took the " + thingCheck.getName() + " and placed it in your bag";
    }

    /**
     * Returns the command name used in the parser.
     *
     * @return the command keyword ("Take")
     */
    public String getName() {
        return NAME;
    }
}
