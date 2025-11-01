package logic.commands;

import logic.characters.Character;
import logic.characters.Dwarf;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;

/**
 * TalkCommand implements the Command interface and allows the player
 * to initiate a conversation with a character present in the current room.
 *
 * The command syntax is: talk [characterName]
 * Example: talk owl
 *
 * The command ignores characters named "Spider" (no talking allowed).
 * If the character is present and valid, their message is returned.
 * Otherwise, an error message is shown.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class TalkCommand implements Command{
    private static final String NAME = "Talk";
    private GamePlan gamePlan;

    /**
     * Constructs the TalkCommand with the current game plan context.
     *
     * @param gamePlan the active game plan that holds room and player state
     */
    public TalkCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    /**
     * Executes the "talk" command to speak with a character in the current room.
     *
     * @param parameters should contain exactly one argument: the name of the character to talk to
     * @return the character's message or an error if the character is not present or cannot be talked to
     */
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

    /**
     * Returns the command name used in the parser.
     *
     * @return the command keyword ("Talk")
     */
    public String getName() {
        return NAME;
    }
}
