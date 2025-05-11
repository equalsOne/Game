package logic.commands;

public interface Command {
    String ExecuteCommand(String... parameters);

    String getName();
}
