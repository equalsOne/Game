package test;

import logic.things.*;
import logic.gamelogic.*;
import logic.commands.*;
import logic.characters.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the game mechanics and commands.
 *
 * Tests cover:
 * - Picking up items (TakeCommand)
 * - Buying items (BuyCommand)
 * - Combat interactions (AttackCommand)
 * - Unlocking treasures (OpenCommand)
 * - Answering riddles (AnswerCommand)
 * - Bribing characters (BribeCommand)
 * - Using teleport (TeleportCommand)
 * - Bag capacity and carriability constraints
 *
 * Before each test, the game state is reset, and the bag is cleared.
 *
 * These tests validate the correct game state changes and expected outputs
 * in response to various player commands.
 *
 * Author: Ihor Skosar
 * Version: LS 2024/2025, June 2025
 */
public class GameTests {
    private Game game;
    private GamePlan gamePlan;
    private TakeCommand takeCommand;
    private BuyCommand buyCommand;
    private AttackCommand attackCommand;
    private OpenCommand openCommand;
    private AnswerCommand answerCommand;
    private BribeCommand bribeCommand;
    private TeleportCommand teleportCommand;

    @BeforeEach
    public void setUp() {
        game = new Game();
        gamePlan = game.getGamePlan();
        takeCommand = new TakeCommand(gamePlan);
        buyCommand = new BuyCommand(gamePlan);
        attackCommand = new AttackCommand(gamePlan);
        openCommand = new OpenCommand(gamePlan, game);
        answerCommand = new AnswerCommand(gamePlan);
        bribeCommand = new BribeCommand(gamePlan);
        teleportCommand = new TeleportCommand(game);

        Bag.getInstance().getThingsNamesInBag().forEach(name -> Bag.getInstance().removeThing(name));
    }

    // Example test verifying the player can pick up coins
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

    @Test
    public void testOpenFailsWithoutKey(){
        Room treasureRoom = gamePlan.getRooms().get("TreasureRoom");
        gamePlan.setCurrentRoom(treasureRoom);

        String result = openCommand.ExecuteCommand();
        assertEquals("The treasure is locked. You need a key to open it", result);
        assertFalse(game.isOver());
    }

    @Test
    public void testAnswerCorrect(){
        Room treasureRoom = gamePlan.getRooms().get("TreasureRoom");
        gamePlan.setCurrentRoom(treasureRoom);

        String response = answerCommand.ExecuteCommand("echo");

        assertEquals("Correct! You may now proceed to open the treasure", response);
    }

    @Test
    public void testAnswerIncorrect(){
        Room treasureRoom = gamePlan.getRooms().get("TreasureRoom");
        gamePlan.setCurrentRoom(treasureRoom);

        String response = answerCommand.ExecuteCommand("wind");

        assertEquals("No! You have 1 attempt(s) left", response);
    }

    @Test
    public void testCoinsCaveContainsCoins(){
        Room coinsCave = gamePlan.getRooms().get("CoinsCave");
        gamePlan.setCurrentRoom(coinsCave);

        assertTrue(gamePlan.getCurrentRoom().getThingsInRoom().stream()
                .map(thing -> thing.getName().toLowerCase())
                .anyMatch(name -> name.equals("coins")));
    }

    @Test
    public void testBribeGuardWithOrb() {
        Room treasureRoom = gamePlan.getRooms().get("TreasureRoom");
        gamePlan.setCurrentRoom(treasureRoom);

        Bag.getInstance().addThing(new Thing("orb", 1, true));

        TreasureGuard guard = (TreasureGuard) treasureRoom.getCharactersInRoom().stream()
                .filter(c -> c instanceof TreasureGuard)
                .findFirst()
                .orElse(null);

        assertNotNull(guard);
        assertFalse(guard.isBribed());

        String result = bribeCommand.ExecuteCommand();

        assertEquals("The guard accepts the magical orb with a nod. \nYou may now try to solve the riddle without limit", result);
        assertTrue(guard.isBribed());
        assertFalse(Bag.getInstance().hasThing("orb"));
    }

    @Test
    public void testTeleportCommandEndsGame() {
        String result = teleportCommand.ExecuteCommand();

        assertTrue(game.isOver(), "Game should be marked as over after teleport");

        assertEquals(
                "You touched the portal and got lost in a void dimension...\n" +
                        "You are dead.\n" +
                        "Don't repeat this mistake the next time!",
                result
        );
    }

    @Test
    public void testCannotTakeUncarriableThing() {
        Thing shield = new Thing("Shield", false);

        Room currentRoom = gamePlan.getCurrentRoom();
        currentRoom.addToThingsInRoom(shield);

        String result = takeCommand.ExecuteCommand("Shield");

        assertEquals("You can't carry this thing", result);
        assertFalse(Bag.getInstance().hasThing("Shield"));
        assertTrue(currentRoom.getThingsInRoom().contains(shield));
    }

    @Test
    public void testCannotTakeWhenBagIsFull() {
        Thing bigThing = new Thing("Something", 5, true);
        Bag.getInstance().addThing(bigThing);

        Thing book = new Thing("Book", 1, true);
        Room currentRoom = gamePlan.getCurrentRoom();
        currentRoom.addToThingsInRoom(book);

        String result = takeCommand.ExecuteCommand("Book");

        assertEquals("You can't carry more things, your bag is full", result);
        assertFalse(Bag.getInstance().hasThing("Book"));
        assertTrue(currentRoom.getThingsInRoom().contains(book));
    }

    @Test
    public void testCannotBuySameItemTwice() {
        Room dwarfHut = gamePlan.getRooms().get("DwarfHut");
        gamePlan.setCurrentRoom(dwarfHut);

        Bag.getInstance().addThing(new Coins());
        buyCommand.ExecuteCommand("Sword");

        boolean firstSword = Bag.getInstance().hasThing("Sword");
        assertTrue(firstSword);

        String response = buyCommand.ExecuteCommand("Sword");

        assertEquals("You don't have enough coins to buy anything", response);
    }

    @Test
    public void testFullCombat() {
        Room coinsCave = gamePlan.getRooms().get("CoinsCave");
        gamePlan.setCurrentRoom(coinsCave);
        takeCommand.ExecuteCommand("Coins");

        assertTrue(Bag.getInstance().hasThing("Coins"));

        Room dwarfHut = gamePlan.getRooms().get("DwarfHut");
        gamePlan.setCurrentRoom(dwarfHut);
        buyCommand.ExecuteCommand("Sword");

        assertTrue(Bag.getInstance().hasThing("Sword"));

        Room spiderCave = gamePlan.getRooms().get("SpiderCave");
        gamePlan.setCurrentRoom(spiderCave);
        attackCommand.ExecuteCommand("Spider");

        assertTrue(Bag.getInstance().hasThing("SpiderSkin"));
        boolean spiderStillAlive = spiderCave.getCharactersInRoom().stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase("Spider"));

        assertFalse(spiderStillAlive);
    }
}
