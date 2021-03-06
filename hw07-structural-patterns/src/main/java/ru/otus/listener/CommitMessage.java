package ru.otus.listener;

import ru.otus.Message;

public class CommitMessage {

    private final Message oldMessage;

    private final Message newMessage;

    public CommitMessage(Message oldMessage, Message newMessage) {
        this.oldMessage = oldMessage;
        this.newMessage = newMessage;
    }

    public Message getOldMessage() {
        return oldMessage;
    }

    public Message getNewMessage() {
        return newMessage;
    }
}
