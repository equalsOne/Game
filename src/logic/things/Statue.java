package logic.things;

/**
 * Represents a statue object in the game.
 * The statue is not carriable.
 * It contains a hint message that players can read.
 *
 * Author: Ihor Skosar
 * Version: LS 2024/2025, June 2025
 */
public class Statue extends Thing{
    /**
     * The hint message displayed when reading the statue.
     */
    private static final String HINT = "If you want to find the treasures " +
            "and leave the caves, " +
            "you have to kill the spider in order to save the wizard, " +
            "\nand he will give you the secret key to the gems! " +
            "\nBut be cautious: only the dwarf's sword can defeat the spider. " +
            "Try to buy it!";

    /**
     * Constructs a Statue object with name "statue" and not carriable.
     */
    public Statue() { super("statue", false); }


    /**
     * Returns the hint message inscribed on the statue.
     *
     * @return hint message as a String
     */
    public String read() {
        return HINT;
    }
}

