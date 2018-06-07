package com.heshammassoud.models.hipchatrequest;

import io.evanwong.oss.hipchat.v2.rooms.RoomItem;

import javax.annotation.Nonnull;


/**
 * * This is how the JSON representation of this bean looks like.
 * <code>
 * message: {
 * date: '2015-01-20T22:45:06.662545+00:00',
 * from: {
 * id: 1661743,
 * mention_name: 'Blinky',
 * name: 'Blinky the Three Eyed Fish'
 * },
 * id: '00a3eb7f-fac5-496a-8d64-a9050c712ca1',
 * mentions: [],
 * message: '/weather',
 * type: 'message'
 * },
 * room: {
 * id: 1147567,
 * name: 'The Weather Channel'
 * }
 * </code>
 **/
public class WebhookItem {
    private RoomMessage message;
    private RoomItem room;

    public WebhookItem() {
    }

    public WebhookItem(@Nonnull final RoomMessage message, @Nonnull final RoomItem room) {
        this.message = message;
        this.room = room;
    }

    public RoomMessage getMessage() {
        return message;
    }

    public void setMessage(@Nonnull final RoomMessage message) {
        this.message = message;
    }

    public RoomItem getRoom() {
        return room;
    }

    public void setRoom(@Nonnull final RoomItem room) {
        this.room = room;
    }
}
