package logic.commands;

import logic.characters.Wizard;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;

public class TradeCommand implements Command{
    private static final String NAME = "Trade";
    private GamePlan gamePlan;

    public TradeCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    public String ExecuteCommand(String... parameters) {
        if (parameters.length > 0) {
            return "To trade, just type 'trade'. " +
                    "You don't need additional parameters";
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

    public String getName() {
        return NAME;
    }
}
