package com.youxu.pattern.statemachine;

public class Message {
    private MessageType messageType;
    private Object messageContent;

    public Message(MessageType messageType, Object messageContent) {
        this.messageType = messageType;
        this.messageContent = messageContent;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public Object getMessageContent() {
        return messageContent;
    }
}
