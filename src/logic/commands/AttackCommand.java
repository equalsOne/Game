package logic.commands;

import logic.characters.*;
import logic.characters.Character;
import logic.things.*;
import logic.gamelogic.*;

public class AttackCommand implements Command{
    private static final String name = "Attack";
    private final GamePlan gamePlan;

    public AttackCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    public String ExecuteCommand(String... parameters) {
        if (parameters.length == 0) {
            return "Who do you want to attack? Please specify a target";
        }

        if (parameters.length > 1 ||
                !parameters[0].equalsIgnoreCase("spider")) {
            return "You can only attack the spider " +
                    "\nPlease specify 'attack spider'";
        }

        boolean hasSword = Bag.getInstance().getThingsNamesInBag().stream()
                .anyMatch(name -> name.equalsIgnoreCase("sword"));

        if (!hasSword) {
            return "You need a sword to attack!";
        }

        Room currentRoom = gamePlan.getCurrentRoom();

        Character spiderCharacter =
                currentRoom.getCharactersInRoom().stream()
                .filter(c -> c instanceof Spider)
                .findFirst()
                .orElse(null);

        if (spiderCharacter == null) {
            return "There is no spider here to attack";
        }

        Spider spider = (Spider) spiderCharacter;

        spider.die();

        currentRoom.removeCharacter(spider);

        Bag.getInstance().addThing(new SpiderSkin());

        return "\nYou have killed the spider! " +
                "You picked up its skin. " +
                "\nThe Wizard has been notified and may now reward you";
    }

    public String getName() {
        return name;
    }
}
