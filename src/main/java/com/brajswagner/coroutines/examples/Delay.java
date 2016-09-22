package com.brajswagner.coroutines.examples;

import com.brajswagner.coroutines.lib.Coroutine;

public class Delay extends Coroutine<Void> {
    private long endTime;
    public Delay(double seconds) {
        this.endTime = System.currentTimeMillis() + (long)(seconds * 1000.0);
    }
    @Override
    public Void run() throws InterruptedException {
        while (System.currentTimeMillis() < endTime) {
            yield(null);
        }
        return null;
    }
}
