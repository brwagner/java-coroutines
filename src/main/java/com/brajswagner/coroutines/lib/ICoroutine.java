package com.brajswagner.coroutines.lib;

/**
 * A convenient construct for cooperative multitasking. A coroutine can transfer
 * control to other coroutines when blocking. When a coroutine calls yield in the
 * run method on a coroutine, the coroutine will wait for the yielded coroutine
 * to finish. The result of the finished coroutine will be returned from the
 * yield method.
 * @param <R> Return value of the coroutine
 */
public interface ICoroutine<R> {

    /**
     * whether the coroutine is still running
     */
    boolean hasNext();

    /**
     * Gets the next item from the coroutine's internal generator. Null implies the coroutine
     * is empty (completes immediately) A non-null coroutine implies this coroutine
     * should wait for the next coroutine to complete
     * @return null or ICoroutine<R>
     */
    ICoroutine<?> next();

    /**
     * Starts the coroutine.
     * @return The return value of the coroutine. Will be passed to the caller.
     * @throws InterruptedException The internal generator is interrupted
     */
    R run() throws InterruptedException;

    /**
     * Cache of the result of running the coroutine.
     * @return The return value of the coroutine. Will be non-null after run completes.
     */
    R getResult();
}
