package logic;

public interface Command {
    String ExecuteCommand(String... parameters);

    String getName();
}
