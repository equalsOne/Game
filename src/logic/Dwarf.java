package logic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Dwarf extends CharacterBase{
    private Map<String, ThingBase> goods;

    public Dwarf(){
        super("Dwarf", "Hi, I'm the Cave Dwarf! " +
                "Do you have any coins? I can sell you something precious. " +
                "Write 'Market' and I'll show you what I have");

        goods = new HashMap<>();

        addGoods(new Sword());
    }

    private void addGoods(ThingBase thing){
        goods.put(thing.getName().toLowerCase(), thing);
    }

    public void deleteGood(ThingBase thing){
        goods.remove(thing.getName());
    }
}
