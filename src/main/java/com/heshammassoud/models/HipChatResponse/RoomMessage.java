package com.heshammassoud.models.HipChatResponse;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * {
 "color": "green",
 "message": "It's going to be sunny tomorrow! (yey)",
 "notify": false,
 "message_format": "text"
 }
 */
public final class RoomMessage {
    private String color;
    private String message;
    private boolean notify;
    @JsonAlias("message_format")
    private String messageFormat;

    private RoomMessage() {
    }

    RoomMessage(String color, String message, boolean notify, String messageFormat) {
        this.color = color;
        this.message = message;
        this.notify = notify;
        this.messageFormat = messageFormat;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public String getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }
}
