package logic.things;

public class Statue extends Thing{
    private static final String HINT = "If you want to find the treasures " +
            "and leave the caves, " +
            "you have to kill the spider in order to save the wizard, " +
            "\nand he will give you the secret key to the gems! " +
            "\nBut be cautious: only the dwarf's sword can defeat the spider. " +
            "Try to buy it!";

    public Statue() { super("statue", false); }

    public String read() {
        return HINT;
    }
}

