package com.brajswagner.coroutines.examples;

public class Result<T, E> {

    private T result;
    public T getResult() { return result; }

    private E error;
    public E getError() { return error; }

    public boolean ok() {
        return this.error == null && this.result != null;
    }

    private Result(T result, E error) {
        this.result = result;
        this.error = error;
    }

    public static <T, E> Result<T, E> success(T result) {
        return new Result<T, E>(result, null);
    }

    public static <T, E> Result<T, E> error(E error) {
        return new Result<T, E>(null, error);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result<?, ?> result1 = (Result<?, ?>) o;

        if (getResult() != null ? !getResult().equals(result1.getResult()) : result1.getResult() != null) return false;
        return getError() != null ? getError().equals(result1.getError()) : result1.getError() == null;

    }

    @Override
    public int hashCode() {
        int result1 = getResult() != null ? getResult().hashCode() : 0;
        result1 = 31 * result1 + (getError() != null ? getError().hashCode() : 0);
        return result1;
    }
}
