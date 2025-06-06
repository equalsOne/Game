package logic.commands;

import logic.characters.Character;
import logic.characters.Dwarf;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;

public class TalkCommand implements Command{
    private static final String NAME = "Talk";
    private GamePlan gamePlan;

    public TalkCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    public String ExecuteCommand(String... parameters) {
        if (parameters.length == 0) {
            return "Who do you want to talk to? Write the " +
                    "character's name after 'talk'";
        }

        String characterName = parameters[0];

        Room currentRoom = gamePlan.getCurrentRoom();

        Character character = currentRoom.getCharactersInRoom().stream()
                .filter(c -> c.getName().equalsIgnoreCase(characterName))
                .filter(c -> !c.getName().equalsIgnoreCase("Spider"))
                .findFirst()
                .orElse(null);

        if (character != null) {
            return character.getMessageForPlayer();
        }
        else { return "There's no one here with that name to talk to"; }
    }

    public String getName() {
        return NAME;
    }
}
