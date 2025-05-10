package logic;

public class CharacterBase {
    private String name;
    private String messageForPlayer;

    public CharacterBase(String name, String messageForPlayer){
        this.name = name;
        this.messageForPlayer = messageForPlayer;
    }

    public CharacterBase(String name)
    { this.name = name; }
}
