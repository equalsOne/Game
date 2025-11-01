package logic.commands;

import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;
import logic.things.Statue;
import logic.things.Thing;

/**
 * ReadCommand implements the Command interface and allows the player
 * to read messages or inscriptions on objects in the current room.
 *
 * The command syntax is: read [thingName]
 * Example: read statue
 *
 * Currently, only the Statue object can be read.
 * If the specified object is not present or not readable,
 * appropriate messages are returned.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class ReadCommand implements Command{
    private static final String NAME = "Read";
    private GamePlan gamePlan;

    /**
     * Constructs the ReadCommand with the current game plan context.
     *
     * @param gamePlan the active game plan that holds room and player state
     */
    public ReadCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }


    /**
     * Executes the "read" command to read inscriptions or messages
     * on a specified object in the current room.
     *
     * @param parameters should contain exactly one argument: the name of the thing to read
     * @return the message read from the object, or an error message if reading is not possible
     */
    public String ExecuteCommand(String... parameters) {
        if (parameters.length != 1) {
            return "Read what? Maybe you wanted to read " +
                    "the message on the statue? Try typing 'read statue'";
        }

        String thingName = parameters[0];
        Room currentRoom = gamePlan.getCurrentRoom();

        Thing thing = currentRoom.getThingsInRoom().stream()
                .filter(t -> t.getName().equalsIgnoreCase(thingName))
                .findFirst()
                .orElse(null);

        if (thing == null) {
            return "There is no " + thingName + " here to read";
        }

        if (thing instanceof Statue) {
            Statue statue = (Statue) thing;

            return statue.read();

        }
        else {
            return "You can't read the " + thingName;
        }
    }

    /**
     * Returns the command name used in the parser.
     *
     * @return the command keyword ("Read")
     */
    public String getName() {
        return NAME;
    }
}
