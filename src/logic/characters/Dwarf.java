package logic.characters;

import logic.things.Sword;
import logic.things.Thing;

import java.util.HashMap;
import java.util.Map;

/*******************************************************************************
 * Class Dwarf extends the Character class, it's the trading character
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class Dwarf extends Character {
    private Map<String, Thing> goods;

    /**
     *  Class constructor
     */
    public Dwarf(){
        super("Dwarf", "Dwarf: 'Hi, I'm the Cave Dwarf! " +
                "\nDo you have any coins? Type 'buy' and I will " +
                "sell you something precious'");

        goods = new HashMap<>();

        addGoods(new Sword());
    }

    /**
     * Adds a good to the dwarf's market
     *
     * @param thing the thing that will be added
     */
    private void addGoods(Thing thing){
        goods.put(thing.getName().toLowerCase(),
                thing);
    }

    /**
     * Deletes a good from dwarf's market
     *
     * @param thing the thing that will be deleted
     */
    public void deleteGood(Thing thing){
        goods.remove(thing.getName());
    }
}
