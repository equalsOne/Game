package logic.gamelogic;

public interface IGame {
    String inceptionMessage();

    String endMessage();

    boolean isOver();

    String handleCommand(String command);

    GamePlan getGamePlan();

    void setOver(boolean isOver);
}
