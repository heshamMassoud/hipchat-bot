package com.heshammassoud.models;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class HipChatRoomBuilder {

    private String name;
    private String id;

    private HipChatRoomBuilder(@Nullable final String name, @Nullable final String id) {
        this.name = name;
        this.id = id;
    }

    @Nonnull
    public static HipChatRoomBuilder ofName(@Nonnull final String name) {
        return new HipChatRoomBuilder(name, null);
    }

    @Nonnull
    public static HipChatRoomBuilder ofId(@Nonnull final String id) {
        return new HipChatRoomBuilder(null, id);
    }

    @Nonnull
    public HipChatRoom build() {
        return new HipChatRoom(this.name, this.id);
    }
}
