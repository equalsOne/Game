package logic.characters;

import logic.gamelogic.Observable;
import logic.gamelogic.Observer;

import java.util.ArrayList;
import java.util.List;
//observable
public class Spider extends Character implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private boolean isAlive;

    public Spider(){
        super("Spider");

        this.isAlive = true;
    }

    public void die() {
        this.isAlive = false;

        notifyObservers();
    }

    public boolean isAlive(){
        return isAlive;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
