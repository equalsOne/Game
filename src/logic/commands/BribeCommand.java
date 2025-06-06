package logic.commands;

import logic.characters.TreasureGuard;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;
import logic.things.Bag;
import logic.things.Thing;

public class BribeCommand implements Command{
    private static final String NAME = "Bribe";
    private GamePlan gamePlan;

    public BribeCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    public String ExecuteCommand(String... parameters) {
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

    public String getName() { return NAME; }
}
