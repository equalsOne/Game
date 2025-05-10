package logic;

import java.util.HashMap;
import java.util.Map;

public class GamePlan {
    private Room currentRoom;
    private Map<String, Room> rooms;
    private Bag bag = new Bag();

    public GamePlan(){
        this.rooms = new HashMap<>();

        RoomFactory factory = new RoomFactory();

        createGameMap(factory);
    }

    public Bag getBag() { return bag; }

    public Map<String, Room> getRooms() { return rooms; }

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

        CharacterBase dwarf = new Dwarf();
        dwarfHut.addCharacter(dwarf);

        ThingBase coins = new Coins();
        coinsCave.addToThingsInRoom(coins);

        currentRoom = startingRoom;
    }

    public Room getCurrentRoom() { return currentRoom; }

    public void setCurrentRoom(Room room) { currentRoom = room; }
}
