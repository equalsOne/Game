package logic.commands;

import logic.things.Bag;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;

/**
 * InformationCommand implements the Command interface and provides detailed
 * information about a specified room or the player's bag contents.
 *
 * The command syntax is: information [roomName|bag]
 * Example: information bag
 *          information wizardSpace
 *
 * If the parameter is "bag", it lists the items currently in the player's bag.
 * If the parameter matches a room name, it returns the detailed information
 * description of that room.
 *
 * If the parameter is missing or invalid, an error message is returned.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class InformationCommand implements Command{
    private static final String NAME = "Information";
    private GamePlan gamePlan;

    /**
     * Constructs the InformationCommand with the current game plan context.
     *
     * @param gamePlan the active game plan that holds room and player state
     */
    public InformationCommand(GamePlan gamePlan){
        this.gamePlan = gamePlan;
    }

    /**
     * Returns the command name used in the parser.
     *
     * @return the command keyword ("Information")
     */
    public String getName() { return NAME; }

    /**
     * Executes the "information" command to retrieve information
     * about a room or the player's bag contents.
     *
     * @param parameters should contain exactly one argument: either a room name or "bag"
     * @return a descriptive message about the requested object or an error if invalid
     */
    public String ExecuteCommand(String... parameters){
        if(parameters.length != 1){
            return "This command doesn't exist. " +
                    "Type the name of the room or 'bag' " +
                    "(e.g. 'information bag') to see the " +
                    "whole information on that object.";
        }

        String message = parameters[0].toLowerCase();

        if(message.equals("bag")){
            return Bag.getInstance().getThingsNamesInBag().isEmpty() ?
                    "Your bag is empty!" :
                    "Things in your bag are: " + String.join(", ",
                            Bag.getInstance().getThingsNamesInBag());
        }

        return gamePlan.getRooms().values().stream()
                .filter(room -> room.getName().equalsIgnoreCase(message))
                .findFirst()
                .map(Room::informationDescription)
                .orElse("There is no such room. Check the name again");
    }
}
