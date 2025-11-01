package logic.characters;

import logic.gamelogic.Observable;
import logic.gamelogic.Observer;

import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 * Class Spider is the monster character, extends the Character base
 * @author    Ihor Skosar
 * @version   LS 2024/2025, June 2025
 */
public class Spider extends Character implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private boolean isAlive;

    /**
     *  Class constructor
     */
    public Spider(){
        super("Spider");

        this.isAlive = true;
    }

    /**
     * spider dead
     *
     */
    public void die() {
        this.isAlive = false;

        notifyObservers();
    }

    /**
     * Returns the boolean of whether the spider is alive or not
     *
     * @return isAlive
     */
    public boolean isAlive(){
        return isAlive;
    }

    /**
     * Sets the observer of the spider
     *
     * @param observer is the observer which will be added
     *
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notifies the observes
     *
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
