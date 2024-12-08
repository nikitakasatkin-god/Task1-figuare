package com.example.laboratornaya2;

import java.util.ArrayDeque;
import java.util.Queue;

public class Caretaker {
    private Queue<Momento> mementoList = new ArrayDeque<>();

    public void saveState(Momento state) {
        mementoList.add(state);
    }

    public Momento retrieveState() {
        return mementoList.poll();
    }
}