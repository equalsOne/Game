package logic.gamelogic;

import logic.commands.*;

import java.util.Arrays;

public class Game implements IGame {
    private CommandsList commandsList;
    private GamePlan gamePlan;
    private boolean isOver = false;

    public Game(){
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
        commandsList.addCommand(new AttackCommand(gamePlan));
        commandsList.addCommand(new TradeCommand(gamePlan));
        commandsList.addCommand(new OpenCommand(gamePlan, this));
        commandsList.addCommand(new ReadCommand(gamePlan));
        commandsList.addCommand(new HelpOwlCommand(gamePlan));
        commandsList.addCommand(new AnswerCommand(gamePlan));
        commandsList.addCommand(new BribeCommand(gamePlan));
        commandsList.addCommand(new TeleportCommand(this));
    }

    public String inceptionMessage() {
        return "Hi! \nThis is a game about treasure hunting in the caves. " +
                "\n \nUse the command 'go' to move between the spaces " +
                "and the command 'talk' to talk to characters" +
                "\n \nTo get a prompt on how to get out of the caves, " +
                "you can use the message on the statue - 'read'" +
                "\n \nUse 'information {name of the room}', e.g. 'information start'" +
                " to figure where you are" +
                "\n \nIf you need an extended list of commands, " +
                "type 'prompt' in the command line" +
                "\n \nOut of curiosity, you may also try " +
                "portal using the 'teleport' command and see what happens!"
                + "\n \nYou are now in the Start \n \nGood luck!";
    }

    public String endMessage() {
        return "That is it! Thanks for playing";
    }

    public boolean isOver() { return isOver; }

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

    public GamePlan getGamePlan() {
        return gamePlan;
    }

    public void setOver(boolean isOver) { this.isOver = isOver; }
}
