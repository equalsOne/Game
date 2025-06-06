package logic.commands;

import logic.characters.Owl;
import logic.gamelogic.GamePlan;
import logic.gamelogic.Room;
import logic.things.Bag;
import logic.things.MagicOrb;
import logic.things.Thing;

public class HelpOwlCommand implements Command{
    private static final String NAME = "Help";
    private GamePlan gamePlan;

    public HelpOwlCommand(GamePlan gamePlan) {
        this.gamePlan = gamePlan;
    }

    public String ExecuteCommand(String... parameters) {
        Room currentRoom = gamePlan.getCurrentRoom();

        Owl owl = (Owl) currentRoom.getCharactersInRoom().stream()
                .filter(c -> c instanceof Owl)
                .findFirst()
                .orElse(null);

        if (owl == null) {
            return "There's no owl here to help";
        }

        if (owl.questCompleted()) {
            return "You've already helped the owl";
        }

        int stage = owl.getDialogueStage();

        if (parameters.length < 2) {
            switch (stage) {
                case 0:
                    owl.setDialogueStage(1);

                    return "Could you remind me where I left something precious? " +
                            "In the forest, in the mountains or in " +
                            "the room coinscave?";

                case 1:
                    return "Please answer the first question: forest, mountains " +
                            "or coinscave?";

                case 2:
                    return "What type of the thing it is? Stone, sword or orb?";

                default:
                    owl.setDialogueStage(0);

                    return "Let's start over. Type 'help owl' to begin";
            }
        }

        String answer = parameters[1].toLowerCase();

        switch (stage) {
            case 1:
                if (answer.equals("coinscave")) {
                    owl.setDialogueStage(2);
                    return "Hmm... yes, I remember now! And what kind of item " +
                            "was it? A stone, a sword or an orb?";
                } else {
                    return "No, that doesn't sound right. Try again: " +
                            "forest, mountains or coinscave?";
                }

            case 2:
                if (answer.equals("orb")) {
                    owl.setQuestCompleted(true);

                    Thing orb = new MagicOrb();

                    if (Bag.getInstance().addThing(orb)) {
                        return "Yes! That's it! The magical orb! Here, " +
                                "take it — you've earned it!";
                    }
                    else {
                        currentRoom.addToThingsInRoom(orb);

                        return "Yes! That's it! The magical orb! " +
                                "But your bag is full...\n" +
                                "You don't have enough space in your bag, " +
                                "so the orb is now on the ground.\n" +
                                "When you'll have enough space in the bag, " +
                                "you can take it!";
                    }
                }
                else {
                    return "No, that doesn’t seem correct. Try again: " +
                            "stone, sword or orb?";
                }

            default:
                owl.setDialogueStage(0);

                return "The owl looks confused... Let's start " +
                        "again with 'help owl'.";
        }
    }

    public String getName() {
        return NAME;
    }
}
