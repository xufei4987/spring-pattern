package com.youxu.pattern.statemachine;

public enum StateType {

    STOPPED(1),
    CLOSED(2),
    OPENING(3),
    OPENED(4),
    CLOSING(5),
    RUNNING(6);

    private int value;

    public int getValue() {
        return value;
    }

    StateType(int value) {
        this.value = value;
    }
}
