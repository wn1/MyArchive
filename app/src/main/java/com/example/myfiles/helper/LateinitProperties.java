package com.example.myfiles.helper;
import androidx.annotation.NonNull;

public class LateinitProperties<T> {
    private T parameters;

    public LateinitProperties() {

    }

    public LateinitProperties(T parameters) {
        this.parameters = parameters;
    }

    @NonNull
    public T get() {
        if (parameters == null) {
            throw new NullPointerException();
        }
        return parameters;
    }

    public void set(T parameters) {
        this.parameters = parameters;
    }
}
