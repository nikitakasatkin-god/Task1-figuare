package com.example.laboratornaya2;

import java.util.ArrayDeque;
import java.util.Queue;

public class Caretaker {
    private Queue<Memento> mementoList = new ArrayDeque<>();

    public void saveState(Memento state) {
        mementoList.add(state);
    }

    public Memento retrieveState() {
        return mementoList.poll();
    }
}