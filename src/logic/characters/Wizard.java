package logic.characters;

import logic.gamelogic.Observer;
import logic.things.Bag;
import logic.things.Key;

//observer
public class Wizard extends Character implements Observer {
    private boolean spiderDefeated = false;

    public Wizard()
    {
        super("Wizard", "Hi, I'm Wizard! " +
                "If you save me from the spider I've once mistakenly created, " +
                "I will surely award you");
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
        if (Bag.getInstance().hasThing("SpiderSkin")) {
            Bag.getInstance().removeThing("SpiderSkin");

            Key key = new Key();

            Bag.getInstance().addThing(key);

            return "Thank you for saving me! " +
                    "\nHere is a key for the treasures";
        }
        else { return "You need a spider's skin to trade with me!"; }
    }
}
