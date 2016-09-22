package com.brajswagner.coroutines.lib;

public abstract class Coroutine<R> implements ICoroutine<R> {

    private R result;
    public R getResult() { return result; }

    private Generator<ICoroutine> generator = new Generator<ICoroutine>() {
        @Override
        protected void run() throws InterruptedException {
            result = Coroutine.this.run();
        }
    };

    @Override
    public boolean hasNext() {
        return generator.iterator().hasNext();
    }

    @Override
    public ICoroutine<?> next() {
        return generator.iterator().next();
    }

    protected <T> T yield(ICoroutine<T> coroutine) throws InterruptedException {
        generator.yield(coroutine);
        if (coroutine == null) {
            return null;
        }
        return coroutine.getResult();
    }
}
