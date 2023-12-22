package com.projet.client;

public class Message {
    private final String senderImageUrl;
    private final String messageText;

    public Message(String senderImageUrl, String messageText) {
        this.senderImageUrl = senderImageUrl;
        this.messageText = messageText;
    }

    public String getSenderImageUrl() {
        return senderImageUrl;
    }

    public String getMessageText() {
        return messageText;
    }
}

