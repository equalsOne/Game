package logic.characters;

/*******************************************************************************
 * Class Owl extends the Character class, it's the quest character
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class Owl extends Character{
    private int dialogueStage = 0;
    private boolean questCompleted = false;

    /**
     *  Class constructor
     */
    public Owl() {
        super("Owl", "Owl: 'I am the wise owl of the caves. " +
                "\nIf you want to help me and get rewarded, " +
                "type 'help owl' to begin the quest'");
    }

    /**
     * Returns the stage of the dialogue with the owl
     *
     * @return dialogueStage
     */
    public int getDialogueStage() {
        return dialogueStage;
    }

    /**
     * Sets the stage of the dialogue with the owl
     *
     * @param stage stage that will be set as current
     *
     */
    public void setDialogueStage(int stage) {
        this.dialogueStage = stage;
    }

    /**
     * Returns the boolean value (if the quest is completed or not)
     *
     * @return questCompleted returns boolean whether the quest is completed or not
     *
     */
    public boolean questCompleted() {
        return questCompleted;
    }

    /**
     * Sets the boolean value (if the quest is completed or not)
     *
     * @param completed sets boolean whether the quest is completed or not
     *
     */
    public void setQuestCompleted(boolean completed) {
        this.questCompleted = completed;
    }
}
