package logic.commands;

import logic.characters.Spider;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;

public class GoCommand implements Command{
    private static final String name = "Go";
    private GamePlan gamePlan;

    public GoCommand(GamePlan gamePlan) { this.gamePlan = gamePlan; }

    public String ExecuteCommand(String... parameters) {
        if (parameters.length == 0) {
            return "Enter the name of the room where you want to go.";
        }

        String direction = parameters[0];

        Room currentRoom = gamePlan.getCurrentRoom();

        if (currentRoom.getName().equals("SpiderCave") &&
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
                        "\nYou can't go there until it is defeated.";
            }
        }

        Room aimRoom = currentRoom.getNearbyRoom(direction);

        if (aimRoom != null) {
            gamePlan.setCurrentRoom(aimRoom);

            return aimRoom.extendedDescription();
        }
        else { return "This place doesn't exist..."; }
    }

    private boolean hasAliveSpiderInRoom(String name) {
        Room room = gamePlan.getRooms().get(name);
        if (room != null) {
            return room.getCharactersInRoom().stream()
                    .filter(character -> character instanceof Spider)
                    .anyMatch(character -> ((Spider) character).isAlive());
        }
        return false;
    }

    public String getName() { return name; }
}

