package logic.commands;

import logic.characters.*;
import logic.characters.Character;
import logic.things.*;
import logic.gamelogic.*;

/*******************************************************************************
 * Class AttackCommand implements the Command interface, the command for
 * attacking enemies (currently only the spider).
 * Player must have a sword in their bag to perform the attack.
 * Upon defeating the spider, the wizard can reward the player.
 *
 * If the bag is full, the spider skin is dropped in the room.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class AttackCommand implements Command{
    private static final String NAME = "Attack";
    private GamePlan gamePlan;

    /**
     * Class constructor
     *
     * @param gamePlan the current game plan used to determine the player's location and actions
     */
    public AttackCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    /**
     * Executes the attack command.
     * The player can only attack the spider if they are in the same room and have a sword.
     * If successful, the spider is defeated and its skin is added to the player's bag or dropped in the room.
     *
     * @param parameters the name of the enemy to attack (should be "spider")
     * @return message indicating the result of the attack attempt
     */
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

        SpiderSkin skin = new SpiderSkin();

        if(!Bag.getInstance().addThing(skin)){
            currentRoom.addToThingsInRoom(skin);

            return "\nYou've killed the spider, but your bag is full... " +
                    "\nThe spider skin is now on the ground, " +
                    "you can pick it up when you'll have some space in your bag!";
        }

        return "\nYou have killed the spider! " +
                "You picked up its skin. " +
                "\nThe Wizard has been notified and may now reward you";
    }

    /**
     * Returns the command's name
     *
     * @return NAME - the name of the command
     */
    public String getName() {
        return NAME;
    }
}
