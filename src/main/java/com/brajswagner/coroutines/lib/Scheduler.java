package com.brajswagner.coroutines.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Scheduler implements IScheduler {

    private List<Stack<ICoroutine>> coStacks = new ArrayList<Stack<ICoroutine>>();

    public void startCoroutine(ICoroutine coroutine) {
        Stack<ICoroutine> coStack = new Stack<ICoroutine>();
        coStack.push(coroutine);
        coStacks.add(coStack);
    }

    public void update() {
        for (Iterator<Stack<ICoroutine>> iterator = coStacks.iterator(); iterator.hasNext();) {
            Stack<ICoroutine> coStack = iterator.next();
            if (coStack.empty()) {
                iterator.remove();
            } else {
                ICoroutine co = coStack.peek();
                if (co.hasNext()) {
                    ICoroutine next = co.next();
                    if (next != null) {
                        coStack.push(next);
                    }
                } else {
                    coStack.pop();
                }
            }
        }
    }
}
