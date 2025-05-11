package test;

import logic.things.*;
import logic.gamelogic.*;
import logic.commands.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game;
    private GamePlan gamePlan;
    private TakeCommand takeCommand;
    private BuyCommand buyCommand;
    private AttackCommand attackCommand;

    @BeforeEach
    public void setUp() {
        game = new Game();
        gamePlan = game.getGamePlan();
        takeCommand = new TakeCommand(gamePlan);
        buyCommand = new BuyCommand(gamePlan);
        attackCommand = new AttackCommand(gamePlan);
    }

    @Test
    public void testTakeThing() {
        Room coinsCave = gamePlan.getRooms().get("CoinsCave");

        gamePlan.setCurrentRoom(coinsCave);

        takeCommand.ExecuteCommand("Coins");

        assertTrue(Bag.getInstance().hasThing("Coins"));
    }

    @Test
    public void testBuySword() {
        Room dwarfHut = gamePlan.getRooms().get("DwarfHut");

        gamePlan.setCurrentRoom(dwarfHut);

        Bag.getInstance().addThing(new Coins());

        buyCommand.ExecuteCommand("Sword");

        assertTrue(Bag.getInstance().hasThing("Sword"));
    }

    @Test
    public void testAttackSpider() {
        Room spiderCave = gamePlan.getRooms().get("SpiderCave");

        gamePlan.setCurrentRoom(spiderCave);

        Bag.getInstance().addThing(new Sword());

        attackCommand.ExecuteCommand("Spider");

        assertTrue(Bag.getInstance().hasThing("SpiderSkin"));

        boolean spiderAlive =
                spiderCave.getCharactersInRoom()
                .stream().anyMatch(c ->
                                c.getName().equalsIgnoreCase("Spider"));

        assertFalse(spiderAlive);
    }

}
