package com.youxu.pattern.statemachine.handler.impl;

import com.youxu.pattern.statemachine.Message;
import com.youxu.pattern.statemachine.handler.ILiftHandler;
import org.springframework.stereotype.Component;

@Component
public class CloseCompleteHandler implements ILiftHandler {

    @Override
    public void handle(Message message) {
        System.out.println("---------------" + message.getMessageType().name() + "---------------");
        System.out.println("---------------" + message.getMessageContent() + "---------------");
        System.out.println("---------------CloseCompleteHandler---------------");
    }
}
