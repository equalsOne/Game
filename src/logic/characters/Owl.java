package logic.characters;

public class Owl extends Character{
    private int dialogueStage = 0;
    private boolean questCompleted = false;

    public Owl() {
        super("Owl", "Owl: 'I am the wise owl of the caves. " +
                "\nIf you want to help me and get rewarded, " +
                "type 'help owl' to begin the quest'");
    }

    public int getDialogueStage() {
        return dialogueStage;
    }

    public void setDialogueStage(int stage) {
        this.dialogueStage = stage;
    }

    public boolean questCompleted() {
        return questCompleted;
    }

    public void setQuestCompleted(boolean completed) {
        this.questCompleted = completed;
    }
}
