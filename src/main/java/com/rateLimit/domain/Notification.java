package com.rateLimit.domain;

import java.util.Objects;

public class Notification {
    private String sender;
    private String recipient;
    private String type;
    private String message;
    private Status status;

    public Notification(String sender, String recipient, String type, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.type = type;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(sender, that.sender) && Objects.equals(recipient, that.recipient) && Objects.equals(type, that.type) && Objects.equals(message, that.message) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, recipient, type, message, status);
    }
}
