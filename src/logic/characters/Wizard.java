package logic.characters;

import logic.gamelogic.Observer;
import logic.things.Bag;
import logic.things.Key;

//observer
public class Wizard extends Character implements Observer {
    private boolean spiderDefeated = false;

    public Wizard()
    {
        super("Wizard", "Wizard: 'Hi, I'm Wizard! " +
                "\nIf you save me from the spider I've once mistakenly created, " +
                "I will surely award you' " +
                "\n \nTo trade with the wizard, type 'trade'");
    }

    public void update(){
        this.spiderDefeated = true;

        System.out.println("Wizard: 'You defeated my long-time menace! " +
                "\nThank you, brave man. If you come by my side, " +
                "I can reward you!'");
    }

    public boolean canReward(){
        return spiderDefeated;
    }

    public String tradeWithPlayer() {
        if(!canReward()){
            return "Wizard: 'I can't trade with you while " +
                    "the spider is still alive!'";
        }

        if (Bag.getInstance().hasThing("SpiderSkin")) {
            Bag.getInstance().removeThing("SpiderSkin");

            Key key = new Key();

            Bag.getInstance().addThing(key);

            return "Wizard: 'Thank you for saving me! " +
                    "\nHere is a key for the treasures'";
        }
        else { return "Wizard: 'You need the spider's skin to trade with me!'"; }
    }
}
