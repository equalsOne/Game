package logic.gamelogic;

import logic.characters.*;
import logic.things.*;

import java.util.HashMap;
import java.util.Map;

public class GamePlan {
    private Room currentRoom;
    private Map<String, Room> rooms;

    public GamePlan(){
        this.rooms = new HashMap<>();

        RoomFactory factory = new RoomFactory();

        createGameMap(factory);
    }

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

    public Room getCurrentRoom() { return currentRoom; }

    public void setCurrentRoom(Room room) { currentRoom = room; }
}
