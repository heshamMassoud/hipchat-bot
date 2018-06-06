package com.heshammassoud.models;

import javax.annotation.Nullable;

public final class HipChatRoom {
    private String name;
    private String id;

    HipChatRoom(@Nullable final String name, @Nullable final String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    private HipChatRoom() {
    }
}
