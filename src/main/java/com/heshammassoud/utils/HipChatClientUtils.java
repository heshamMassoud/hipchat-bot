package com.heshammassoud.utils;

import io.evanwong.oss.hipchat.v2.HipChatClient;

import javax.annotation.Nonnull;

public class HipChatClientUtils {
    /**
     * Gets a new instance of a {@link HipChatClient} using the supplied {@code accessToken}.
     * @param accessToken to create Hipchat client with.
     * @return hipchat client.
     */
    public static HipChatClient getHipChatClient(@Nonnull final String accessToken) {
        return new HipChatClient(accessToken);
    }




}
