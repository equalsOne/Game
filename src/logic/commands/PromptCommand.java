package logic.commands;

public class PromptCommand implements Command{
    private static final String NAME = "Prompt";
    private CommandsList commandsList;
    private static final String PROMPT = "You have to unlock the treasures, " +
            "hidden in the treasure room. " +
            "\nYou are going to need key for this. " +
            "In the game, you can use this commands: \n";

    public PromptCommand(CommandsList commandsList) { this.commandsList = commandsList; }

    public String getName() { return NAME; }

    @Override
    public String ExecuteCommand(String... parameters) {
        return parameters.length != 0 ? "Sorry, this command doesn't exist. " +
                "\nMaybe you've meant 'Prompt', which helps you to find out all the commands?"
                : PROMPT + String.join("\n", commandsList.getCommandsNames());
    }
}
