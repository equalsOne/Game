package logic.commands;

import logic.things.Bag;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;

public class InformationCommand implements Command{
    private static final String NAME = "Information";
    private GamePlan gamePlan;

    public InformationCommand(GamePlan gamePlan){
        this.gamePlan = gamePlan;
    }

    public String getName() { return NAME; }

    public String ExecuteCommand(String... parameters){
        if(parameters.length == 0){
            return "This command doesn't exist. " +
                    "Type the name of the room or 'bag' to see the " +
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
                .orElse("There is no such room. Check the name again.");
    }
}
