package logic.gamelogic;

/**
 * Factory class responsible for creating different types of rooms
 * used in the game map. Each method returns a new instance of Room
 * with predefined names and descriptions.
 *
 * Author: Ihor Skosar
 * Version: LS 2024/2025, June 2025
 */
public class RoomFactory {

    /**
     * Creates the starting room where the player's journey begins.
     *
     * @return a new Room instance named "Start"
     */
    public Room createStartingRoom(){
        return new Room("Start",
                "This is where your journey begins");
    }

    /**
     * Creates the dwarf hut room, where a dwarf character can be met.
     *
     * @return a new Room instance named "DwarfHut"
     */
    public Room createDwarfHut(){
        return new Room("DwarfHut",
                "You can meet dwarf here. He can sell you something!");
    }

    /**
     * Creates the coins cave room, where the player might find lost coins.
     *
     * @return a new Room instance named "CoinsCave"
     */
    public Room createCoinsCave(){
        return new Room("CoinsCave",
                "If you are lucky, you will find some lost coins in here!");
    }

    /**
     * Creates the spider cave room, inhabited by a spider.
     *
     * @return a new Room instance named "SpiderCave"
     */
    public Room createSpiderCave(){
        return new Room("SpiderCave",
                "Be careful, spider lives here!");
    }


    /**
     * Creates the wizard's space, where the wizard can assist
     * if the player has the spider's skin.
     *
     * @return a new Room instance named "WizardSpace"
     */
    public Room createWizardSpace(){
        return new Room("WizardSpace",
                "If you have the spider's skin, " +
                        "wizard will unravel the treasures for you!");
    }

    /**
     * Creates the treasure room, the final room with treasures.
     *
     * @return a new Room instance named "TreasureRoom"
     */
    public Room createTreasureRoom(){
        return new Room("TreasureRoom", "Congratulations, " +
                "you've found the magic door to the treasures! " +
                "Now, you can use the key to unlock it " +
                "\nType 'open' to unlock the door");
    }
}
