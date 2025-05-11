package logic.gamelogic;

public interface Game {
    String inceptionMessage();

    String endMessage();

    boolean isOver();

    String handleCommand(String command);

    GamePlan getGamePlan();

    void setOver(boolean isOver);

    //getcurrentroom
    //setcurrentroom
}
