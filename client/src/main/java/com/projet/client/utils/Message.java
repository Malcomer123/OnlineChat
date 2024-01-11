package com.projet.client.utils;

public class Message {
    private int idMessage;
    private String message;
    private String sender;
    private String date;

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public Message(int idMessage, String message, String sender, String date) {
        this.idMessage = idMessage;
        this.message = message;
        this.sender = sender;
        this.date = date;
    }

    public Message( String message, String sender, String date) {
        this.message = message;
        this.sender = sender;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "idMessage=" + idMessage +
                ", message='" + message + '\'' +
                ", sender='" + sender + '\'' +
                ", date='" + date + '\'' +
                '}';

    }
}
