package logic.commands;

import logic.characters.TreasureGuard;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;
import logic.things.Bag;
import logic.things.Thing;

/*******************************************************************************
 * Class BribeCommand implements the Command interface, representing
 * the command to bribe the treasure guard using a magical orb.
 * If successful, the player is allowed to solve the riddle without limits.
 *
 * Usage: Player must have an "orb" item in their bag to bribe the guard.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class BribeCommand implements Command{
    private static final String NAME = "Bribe";
    private GamePlan gamePlan;

    /**
     * Class constructor
     *
     * @param gamePlan the current game plan used to retrieve the room and its characters
     */
    public BribeCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    /**
     * Executes the bribe command.
     * The player can bribe the TreasureGuard with a magical orb.
     * If the orb is present in the bag, it is removed and the guard allows unlimited attempts to solve the riddle.
     *
     * @param parameters should be empty; otherwise an error is returned
     * @return message indicating the result of the bribe attempt
     */
    public String ExecuteCommand(String... parameters) {
        if (parameters.length != 0) {
            return "That's incorrect. Just type 'bribe'";
        }

        Room currentRoom = gamePlan.getCurrentRoom();
        TreasureGuard guard = (TreasureGuard) currentRoom.getCharactersInRoom().stream()
                .filter(c -> c instanceof TreasureGuard)
                .findFirst()
                .orElse(null);

        if (guard == null) { return "There's no one here you can bribe"; }

        if (guard.isBribed()) {
            return "The guard is already satisfied with your offering";
        }

        Bag bag = Bag.getInstance();
        Thing orb = bag.getThingByName("orb");

        if (orb == null) {
            return "You need a magical orb to bribe the guard";
        }

        bag.removeThing(orb);
        guard.setBribed(true);

        return "The guard accepts the magical orb with a nod. " +
                "\nYou may now try to solve the riddle without limit";
    }

    /**
     * Returns the name of the command
     *
     * @return NAME - the command keyword ("bribe")
     */
    public String getName() { return NAME; }
}
