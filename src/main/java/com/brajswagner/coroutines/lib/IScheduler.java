package com.brajswagner.coroutines.lib;

/**
 * Owns the collection of running coroutines and manages the transfer
 * of control between each routine.
 */
public interface IScheduler {

    /**
     * Adds a new coroutine to the schedule
     * @param coroutine the coroutine to add
     */
    void startCoroutine(ICoroutine coroutine);

    /**
     * Transfers control between coroutines
     */
    void update();
}
