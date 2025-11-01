package logic.commands;

import logic.characters.Wizard;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;

/**
 * TradeCommand implements the Command interface and handles trading
 * interactions between the player and a wizard character.
 *
 * The command syntax is: trade
 * No additional parameters are accepted.
 *
 * Trading is only possible if the player is in the "WizardSpace" room
 * and a wizard character is present.
 *
 * If the conditions are met, the wizard initiates a trade dialogue.
 * Otherwise, an appropriate error message is returned.
 *
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class TradeCommand implements Command{
    private static final String NAME = "Trade";
    private GamePlan gamePlan;

    /**
     * Constructs the TradeCommand with the current game plan context.
     *
     * @param gamePlan the active game plan that holds room and player state
     */
    public TradeCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    /**
     * Executes the "trade" command, starting a trade with the wizard
     * if the player is in the correct room and the wizard is present.
     *
     * @param parameters should be empty; if not, returns an error message
     * @return the trade dialogue or an error message if trading is not possible
     */
    public String ExecuteCommand(String... parameters) {
        if (parameters.length > 0) {
            return "To trade, just type 'trade'";
        }

        Room currentRoom = gamePlan.getCurrentRoom();

        Wizard wizard = currentRoom.getCharactersInRoom().stream()
                .filter(c -> c instanceof Wizard)
                .map(c -> (Wizard) c)
                .findFirst()
                .orElse(null);

        if (wizard != null &&
                gamePlan.getCurrentRoom().getName().equals("WizardSpace")) {
            return wizard.tradeWithPlayer();
        }
        else { return "There is no wizard here to trade with"; }
    }

    /**
     * Returns the command name used in the parser.
     *
     * @return the command keyword ("Trade")
     */
    public String getName() {
        return NAME;
    }
}
