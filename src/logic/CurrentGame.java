package logic;

import java.util.Arrays;

public class CurrentGame implements Game{
    private CommandsList commandsList;
    private GamePlan gamePlan;
    private boolean isOver = false;

    public CurrentGame(){
        this.commandsList = new CommandsList();
        this.gamePlan = new GamePlan();

        commandsList.addCommand(new EndCommand(this));
        commandsList.addCommand(new GoCommand(gamePlan));
        commandsList.addCommand(new PromptCommand(commandsList));
        commandsList.addCommand(new InformationCommand(gamePlan));
        commandsList.addCommand(new DisposeCommand(gamePlan));
        commandsList.addCommand(new TalkCommand(gamePlan));
        commandsList.addCommand(new BuyCommand(gamePlan));
        commandsList.addCommand(new TakeCommand(gamePlan));
    }

    @Override
    public String inceptionMessage() {
        return "Hi! \nThis is a game about treasure hunting in the caves. " +
                "\nRight now, you are in the "
                + gamePlan.getCurrentRoom().getName() +
                "\n" + gamePlan.getCurrentRoom().nearbyRoomsDescription() +
                "\nIf you need help to figure out how to play, " +
                "type 'prompt' in the command line";
    }

    @Override
    public String endMessage() {
        return "That is it! Thanks for playing";
    }

    @Override
    public boolean isOver() { return isOver; }

    @Override
    public String handleCommand(String userCommand) {
        String[] commandWords = Arrays.stream(userCommand.trim().split("\\s+"))
                .filter(s -> !s.isBlank())
                .toArray(String[]::new);

        if(commandWords.length == 0) { return "Type some command"; }

        String commandName = commandWords[0];

        String[] parameters = Arrays.copyOfRange(commandWords, 1, commandWords.length);

        Command command = commandsList.getCommand(commandName);

        return command != null ? command.ExecuteCommand(parameters)
                : "This command doesn't exist...";
    }

    @Override
    public GamePlan getGamePlan() {
        return gamePlan;
    }

    @Override
    public void setOver(boolean isOver) { this.isOver = isOver; }
}
