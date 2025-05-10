package logic;

public interface Command {
    String doCommand(String... parameters);

    String getName();
}
