package logic.commands;

import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;
import logic.things.Statue;
import logic.things.Thing;

public class ReadCommand implements Command{
    private static final String NAME = "Read";
    private GamePlan gamePlan;

    public ReadCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    public String ExecuteCommand(String... parameters) {
        if (parameters.length == 0) {
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

    public String getName() {
        return NAME;
    }
}
