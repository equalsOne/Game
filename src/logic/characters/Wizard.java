package logic.characters;

import logic.gamelogic.Observer;
import logic.things.Bag;
import logic.things.Key;

/*******************************************************************************
 * Class Wizard extends the Character base, gives the secret treasure key
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class Wizard extends Character implements Observer {
    private boolean spiderDefeated = false;

    /**
     *  Class constructor
     */
    public Wizard()
    {
        super("Wizard", "Wizard: 'Hi, I'm Wizard! " +
                "\nIf you save me from the spider I've once mistakenly created, " +
                "I will surely award you' " +
                "\n\nTo trade with the wizard, type 'trade'");
    }

    /**
     * Updates the status of spiderDefeated (so that the wizard can trade with the player)
     *
     */
    public void update(){
        this.spiderDefeated = true;

        System.out.println("Wizard: 'You defeated my long-time menace! " +
                "\nThank you, brave man. If you come by my side, " +
                "I can reward you!'");
    }

    /**
     * Returns the boolean of whether the spider is defeated and wizard can reward the player
     *
     * @return spiderDefeated
     */
    public boolean canReward(){
        return spiderDefeated;
    }

    /**
     * Returns the string with trading messages
     *
     * @return different strings
     */
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
                    "\nHere is a key for the treasures' " +
                    "\n\nYou obtained the secret key! " +
                    "Now, you can proceed to the treasure room to open the gems";
        }
        else { return "Wizard: 'You need the spider's skin to trade with me!'"; }
    }
}
