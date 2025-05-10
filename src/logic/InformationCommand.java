package logic;

public class InformationCommand {
    private static final String name = "Information";
    private GamePlan gamePlan;
    private Bag bag;

    public InformationCommand(GamePlan gamePlan, Bag bag){
        this.gamePlan = gamePlan;
        this.bag = bag;
    }

    public String getName() { return name; }

    public String doCommand(String... parameters){
        if(parameters.length == 0 || parameters[0].isBlank()){
            return "This command doesn't exist. " +
                    "Type the name of the room or 'bag' to see the " +
                    "whole information on that object.";
        }

        String message = parameters[0].toLowerCase();

        if(parameters[0].equals("bag")){
            return bag.getThingsInBag().isEmpty() ? "Your bag is empty!" :
                    String.join(" ", bag.getThingsInBag());
        }

        return gamePlan.getRooms().values().stream()
                .filter(room -> room.getName().equalsIgnoreCase(message))
                .findFirst()
                .map(Room::extendedDescription)
                .orElse("There is no such room. Check the name again.");
    }
}
