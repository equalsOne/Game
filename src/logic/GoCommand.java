package logic;

public class GoCommand implements Command{
    private static final String name = "Go";
    private GamePlan gamePlan;

    public GoCommand(GamePlan gamePlan) { this.gamePlan = gamePlan; }

    public String ExecuteCommand(String... parameters) {
        if(parameters.length == 0){
            return "Enter the name of the room where you want to go.";
        }

        String direction = parameters[0];

        Room aimRoom = gamePlan.getCurrentRoom().getNearbyRoom(direction);

        if(aimRoom != null) {
            gamePlan.setCurrentRoom(aimRoom);

            return aimRoom.extendedDescription();
        }
        else { return "This place doesn't exist..."; }
    }

    public String getName() { return name; }
}
