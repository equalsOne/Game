package logic.gamelogic;

/**
 * Interface Observable defines the methods
 * for managing observers in the observer design pattern.
 * Classes implementing this interface allow observers
 * to register and get notified about state changes.
 *
 * @author Ihor Skosar
 * @version LS 2024/2025, June 2025
 */
public interface Observable {

    /**
     * Registers an observer to receive updates.
     *
     * @param observer the Observer to be added
     */
    void addObserver(Observer observer);

    /**
     * Notifies all registered observers of a state change.
     */
    void notifyObservers();
}
