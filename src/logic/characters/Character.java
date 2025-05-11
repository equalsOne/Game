package logic.characters;

public class Character {
    private String name;
    private String messageForPlayer;

    public Character(String name, String messageForPlayer){
        this.name = name;
        this.messageForPlayer = messageForPlayer;
    }

    public Character(String name)
    { this.name = name; }

    public String getName() { return name; }

    public String getMessageForPlayer() { return messageForPlayer; }
}
