package logic.gamelogic;
//factory
public class RoomFactory {
    public Room createStartingRoom(){
        return new Room("Start",
                "This is where your journey begins");
    }

    public Room createDwarfHut(){
        return new Room("DwarfHut",
                "You can meet dwarf here. He can sell you something!");
    }

    public Room createCoinsCave(){
        return new Room("CoinsCave",
                "If you are lucky, you will find some lost coins in here!");
    }

    public Room createSpiderCave(){
        return new Room("SpiderCave",
                "Be careful, spider lives here!");
    }

    public Room createWizardSpace(){
        return new Room("WizardSpace",
                "If you have spider's skin, " +
                        "wizard will unravel the treasures for you!");
    }

    public Room createTreasureRoom(){
        return new Room("TreasureRoom", "Congratulations, " +
                "you've found the magic door to the treasures! " +
                "Now, you can use the key to unlock it");
    }
}
