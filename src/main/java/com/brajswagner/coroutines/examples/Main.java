package com.brajswagner.coroutines.examples;

import com.brajswagner.coroutines.lib.Coroutine;
import com.brajswagner.coroutines.lib.ICoroutine;
import com.brajswagner.coroutines.lib.IScheduler;
import com.brajswagner.coroutines.lib.Scheduler;

public class Main {

    public static ICoroutine<String> runExample() {
        return new Coroutine<String>() {
            @Override
            public String run() throws InterruptedException {
                for (char c : "Hello".toCharArray()) {
                    yield(new Delay(0.2f));
                    System.out.print(c);
                }
                System.out.println(yield(new WWW("http://google.com")).getResult());
                return null;
            }
        };
    }

    public static void main(String... args) throws InterruptedException {
        IScheduler scheduler = new Scheduler();
        scheduler.startCoroutine(runExample());
        scheduler.startCoroutine(runExample());
        while (true) {
            scheduler.update();
            Thread.sleep(1);
        }
    }
}
