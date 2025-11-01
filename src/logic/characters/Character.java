package logic.characters;

/*******************************************************************************
 * Class Character is the base class for all the characters (they extend the base class)
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class Character {
    private String name;
    private String messageForPlayer;

    /**
     *  Class constructor
     *
     *  @param name of the character and message for the player
     */
    public Character(String name, String messageForPlayer){
        this.name = name;
        this.messageForPlayer = messageForPlayer;
    }

    /**
     *  Class constructor
     *
     *  @param name of the character
     */
    public Character(String name)
    { this.name = name; }

    /**
     * Returns the name of the character
     *
     * @return name of the character
     */
    public String getName() { return name; }

    /**
     * Returns the message for the player
     *
     * @return message for the player
     */
    public String getMessageForPlayer() { return messageForPlayer; }
}
