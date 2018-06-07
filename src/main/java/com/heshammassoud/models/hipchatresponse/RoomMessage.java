package com.heshammassoud.models.hipchatresponse;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.annotation.Nonnull;

/**
 * This is how the JSON representation of this bean looks like.
 * <code>
 * {
 * "color": "green",
 * "message": "It's going to be sunny tomorrow! (yey)",
 * "notify": false,
 * "message_format": "text"
 * }
 * </code>
 */
public final class RoomMessage {
    private String color;
    private String message;
    private boolean notify;
    @JsonAlias("message_format")
    private String messageFormat;

    private RoomMessage() {
    }

    RoomMessage(@Nonnull final String color, @Nonnull final String message,
                final boolean notify, @Nonnull final String messageFormat) {
        this.color = color;
        this.message = message;
        this.notify = notify;
        this.messageFormat = messageFormat;
    }

    public String getColor() {
        return color;
    }

    public void setColor(@Nonnull final String color) {
        this.color = color;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(@Nonnull final String message) {
        this.message = message;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(final boolean notify) {
        this.notify = notify;
    }

    public String getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(@Nonnull final String messageFormat) {
        this.messageFormat = messageFormat;
    }
}
