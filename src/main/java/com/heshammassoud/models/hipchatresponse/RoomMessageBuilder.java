package com.heshammassoud.models.hipchatresponse;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.annotation.Nonnull;

public class RoomMessageBuilder {
    private String color;
    private String message;
    private boolean notify;
    @JsonAlias("message_format")
    private String messageFormat;


    private RoomMessageBuilder(@Nonnull final String color, @Nonnull final String message,
                               final boolean notify, @Nonnull final String messageFormat) {
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

    @Nonnull
    public RoomMessageBuilder color(@Nonnull final String color) {
        return new RoomMessageBuilder(color, message, notify, messageFormat);
    }

    @Nonnull
    public RoomMessageBuilder message(@Nonnull final String message) {
        return new RoomMessageBuilder(color, message, notify, messageFormat);
    }

    @Nonnull
    public RoomMessageBuilder notify(final boolean notify) {
        return new RoomMessageBuilder(color, message, notify, messageFormat);
    }

    @Nonnull
    public RoomMessageBuilder messageFormat(@Nonnull final String messageFormat) {
        return new RoomMessageBuilder(color, message, notify, messageFormat);
    }

    @Nonnull
    public RoomMessage build() {
        return new RoomMessage(color, message, notify, messageFormat);
    }

}
