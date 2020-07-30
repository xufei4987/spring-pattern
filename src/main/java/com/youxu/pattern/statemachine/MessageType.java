package com.youxu.pattern.statemachine;

public enum MessageType {

    OPEN(1),
    OPEN_COMPLETE(2),
    CLOSE(3),
    CLOSE_COMPLETE(4),
    RUN(5),
    STOP(6);

    private int value;

    public int getValue() {
        return value;
    }

    MessageType(int value) {
        this.value = value;
    }
}
