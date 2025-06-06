package logic.characters;

import logic.things.MagicOrb;
import logic.things.Sword;
import logic.things.Thing;

import java.util.HashMap;
import java.util.Map;

public class Dwarf extends Character {
    private Map<String, Thing> goods;

    public Dwarf(){
        super("Dwarf", "Dwarf: 'Hi, I'm the Cave Dwarf! " +
                "\nDo you have any coins? Type 'buy' and I will " +
                "sell you something precious'");

        goods = new HashMap<>();

        addGoods(new Sword());
    }

    private void addGoods(Thing thing){
        goods.put(thing.getName().toLowerCase(),
                thing);
    }

    public void deleteGood(Thing thing){
        goods.remove(thing.getName());
    }
}
