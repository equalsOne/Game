package logic.gamelogic;

/**
 * Interface Observer defines the method
 * that observers must implement to receive updates
 * from an Observable subject in the observer pattern.
 *
 * @author Ihor Skosar
 * @version LS 2024/2025, June 2025
 */
public interface Observer {

    /**
     * Called by the Observable to notify this observer
     * about a change in its state.
     */
    void update();
}
