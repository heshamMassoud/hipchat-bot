package com.heshammassoud.models.HipChatResponse;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.annotation.Nonnull;

/**
 * {
 "color": "green",
 "message": "It's going to be sunny tomorrow! (yey)",
 "notify": false,
 "message_format": "text"
 }
 */
public class RoomMessageBuilder {
    private String color;
    private String message;
    private boolean notify;
    @JsonAlias("message_format")
    private String messageFormat;


    private RoomMessageBuilder(String color, String message, boolean notify, String messageFormat) {
        this.color = color;
        this.message = message;
        this.notify = notify;
        this.messageFormat = messageFormat;
    }

    public RoomMessageBuilder() {
    }

    @Nonnull
    public static RoomMessageBuilder of() {
        return new RoomMessageBuilder();
    }

    public RoomMessageBuilder color(@Nonnull final String color) {
        return new RoomMessageBuilder(color, message, notify, messageFormat);
    }

    public RoomMessageBuilder message(@Nonnull final String message) {
        return new RoomMessageBuilder(color, message, notify, messageFormat);
    }

    public RoomMessageBuilder notify(final boolean notify) {
        return new RoomMessageBuilder(color, message, notify, messageFormat);
    }

    public RoomMessageBuilder messageFormat(@Nonnull final String messageFormat) {
        return new RoomMessageBuilder(color, message, notify, messageFormat);
    }

    public RoomMessage build() {
        return new RoomMessage(color, message, notify, messageFormat);
    }

}
