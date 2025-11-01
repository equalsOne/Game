package logic.commands;

import logic.characters.Spider;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;

/*******************************************************************************
 * Class GoCommand implements the Command interface and handles player movement
 * between rooms in the game world.
 *
 * The command syntax is: go [roomName]
 * Example: go wizardSpace
 *
 * It validates whether the destination room is adjacent to the current one,
 * and includes special logic preventing the player from accessing
 * or returning to certain rooms (like WizardSpace or SpiderCave)
 * while a spider is still alive.
 *
 * If the target room exists and movement is allowed,
 * the player is moved and a description of the new room is returned.
 * Otherwise, an error message is shown.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class GoCommand implements Command{
    private static final String NAME = "Go";
    private GamePlan gamePlan;

    /**
     * Constructs the GoCommand with the current game plan context.
     *
     * @param gamePlan the active game plan that holds room and player state
     */
    public GoCommand(GamePlan gamePlan) { this.gamePlan = gamePlan; }

    /**
     * Executes the "go" command. Moves the player to a neighboring room,
     * provided the destination is valid and accessible.
     *
     * @param parameters should contain one argument: the name of the room to go to
     * @return message indicating the result of the attempted movement
     */
    public String ExecuteCommand(String... parameters) {
        if (parameters.length != 1) {
            return "Enter the name of the room where you want to go correctly";
        }

        String direction = parameters[0];

        Room currentRoom = gamePlan.getCurrentRoom();

        if (currentRoom.getName().equalsIgnoreCase("SpiderCave") &&
                direction.equalsIgnoreCase("wizardSpace")) {
            if (currentRoom.hasAliveSpider()) {
                return "There is a spider here! " +
                        "\nYou can't go to the Wizard Space " +
                        "until it is defeated";
            }
        }

        if (currentRoom.getName().equals("WizardSpace")
                && direction.equalsIgnoreCase("spiderCave")) {
            if (hasAliveSpiderInRoom("SpiderCave")) {
                return "There is a spider in the Spider Cave! " +
                        "\nYou can't go there until it is defeated " +
                        "\nTo defeat the spider, use the 'attack' command";
            }
        }

        Room aimRoom = currentRoom.getNearbyRoom(direction);

        if (aimRoom != null) {
            gamePlan.setCurrentRoom(aimRoom);

            return aimRoom.extendedDescription();
        }
        else { return "You can't go there... " +
                "\nYou can only go to the nearby rooms. \n"
                + currentRoom.nearbyRoomsDescription(); }
    }

    /**
     * Helper method to check if the given room contains a living spider.
     *
     * @param name the name of the room to check
     * @return true if the spider in the room is alive; false otherwise
     */
    private boolean hasAliveSpiderInRoom(String name) {
        Room room = gamePlan.getRooms().get(name);

        if (room != null) {
            return room.getCharactersInRoom().stream()
                    .filter(character -> character instanceof Spider)
                    .anyMatch(character -> ((Spider) character).isAlive());
        }

        return false;
    }

    /**
     * Returns the command name used in the parser.
     *
     * @return the command keyword ("Go")
     */
    public String getName() { return NAME; }
}

