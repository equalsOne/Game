package logic.gamelogic;

public interface Observable {
    void addObserver(Observer observer);
    void notifyObservers();
}
