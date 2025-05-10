package logic;

public class PromptCommand implements Command{
    private static final String name = "Prompt";
    private CommandsList commandsList;
    private static final String prompt = "You have to unlock the treasures, " +
            "hidden in the treasure room. " +
            "You are going to need key for this. " +
            "In the game, you can use this commands: \n";

    public PromptCommand(CommandsList commandsList) { this.commandsList = commandsList; }

    public String getName() { return name; }

    @Override
    public String doCommand(String... parameters) {
        return parameters.length != 0 ? "Sorry, this command doesn't exist. " +
                "Maybe you've meant 'Prompt', which helps you to find out all the commands?"
                : prompt + String.join("\n", commandsList.getCommandsNames());
    }
}
