package logic;

public class InformationCommand implements Command{
    private static final String name = "Information";
    private GamePlan gamePlan;
    private Bag bag;

    public InformationCommand(GamePlan gamePlan){
        this.gamePlan = gamePlan;
        this.bag = gamePlan.getBag();
    }

    public String getName() { return name; }

    public String doCommand(String... parameters){
        if(parameters.length == 0){
            return "This command doesn't exist. " +
                    "Type the name of the room or 'bag' to see the " +
                    "whole information on that object.";
        }

        String message = parameters[0].toLowerCase();

        if(message.equals("bag")){
            return bag.getThingsNamesInBag().isEmpty() ? "Your bag is empty!" :
                    String.join(" ", bag.getThingsNamesInBag());
        }

        return gamePlan.getRooms().values().stream()
                .filter(room -> room.getName().equalsIgnoreCase(message))
                .findFirst()
                .map(Room::extendedDescription)
                .orElse("There is no such room. Check the name again.");
    }
}
