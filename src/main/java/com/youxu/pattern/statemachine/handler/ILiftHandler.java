package com.youxu.pattern.statemachine.handler;

import com.youxu.pattern.statemachine.Message;

public interface ILiftHandler {
    void handle(Message message);
}
