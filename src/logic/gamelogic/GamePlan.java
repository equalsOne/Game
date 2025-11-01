package logic.gamelogic;

import logic.characters.*;
import logic.things.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The GamePlan class represents the current state of the game world,
 * including all rooms and the player's current location.
 *
 * It initializes the map of rooms and their connections,
 * populates rooms with characters and items,
 * and provides methods to get and set the current room.
 *
 * @author Ihor Skosar
 * @version LS 2024/2025, June 2025
 */
public class GamePlan {
    private Room currentRoom;
    private Map<String, Room> rooms;

    /**
     * Constructs a new GamePlan, creates all rooms,
     * sets their exits, and populates them with characters and things.
     */
    public GamePlan(){
        this.rooms = new HashMap<>();

        RoomFactory factory = new RoomFactory();

        createGameMap(factory);
    }

    /**
     * Returns the map of all rooms in the game, keyed by room name.
     *
     * @return map of rooms
     */
    public Map<String, Room> getRooms() { return rooms; }

    /**
     * Creates the game map by instantiating rooms using the factory,
     * linking them with exits, adding characters and items to them,
     * and setting the starting room.
     *
     * @param factory the RoomFactory used to create rooms
     */
    private void createGameMap(RoomFactory factory){
        Room startingRoom = factory.createStartingRoom();
        Room dwarfHut = factory.createDwarfHut();
        Room coinsCave = factory.createCoinsCave();
        Room spiderCave = factory.createSpiderCave();
        Room wizardSpace = factory.createWizardSpace();
        Room treasureRoom = factory.createTreasureRoom();

        rooms.put(startingRoom.getName(), startingRoom);
        rooms.put(dwarfHut.getName(), dwarfHut);
        rooms.put(coinsCave.getName(), coinsCave);
        rooms.put(spiderCave.getName(), spiderCave);
        rooms.put(wizardSpace.getName(), wizardSpace);
        rooms.put(treasureRoom.getName(), treasureRoom);

        startingRoom.setExits(dwarfHut, coinsCave);
        dwarfHut.setExits(treasureRoom, startingRoom);
        coinsCave.setExits(startingRoom, spiderCave);
        treasureRoom.setExits(dwarfHut, wizardSpace);
        wizardSpace.setExits(treasureRoom, spiderCave);
        spiderCave.setExits(coinsCave, wizardSpace);

        dwarfHut.addCharacter(new Dwarf());

        Spider spider = new Spider();
        spiderCave.addCharacter(spider);

        Wizard wizard = new Wizard();
        wizardSpace.addCharacter(wizard);

        spider.addObserver(wizard);

        coinsCave.addToThingsInRoom(new Coins());

        startingRoom.addToThingsInRoom(new Statue());

        coinsCave.addCharacter(new Owl());

        treasureRoom.addCharacter(new TreasureGuard());

        startingRoom.addToThingsInRoom(new Portal());

        currentRoom = startingRoom;
    }

    /**
     * Returns the current room where the player is located.
     *
     * @return current room
     */
    public Room getCurrentRoom() { return currentRoom; }


    /**
     * Sets the current room to the specified room.
     *
     * @param room the room to set as current
     */
    public void setCurrentRoom(Room room) { currentRoom = room; }
}
