package com.heshammassoud.models.HipChatRequest;

import io.evanwong.oss.hipchat.v2.rooms.RoomItem;


/*
message: {
            date: '2015-01-20T22:45:06.662545+00:00',
            from: {
                id: 1661743,
                mention_name: 'Blinky',
                name: 'Blinky the Three Eyed Fish'
            },
            id: '00a3eb7f-fac5-496a-8d64-a9050c712ca1',
            mentions: [],
            message: '/weather',
            type: 'message'
        },
        room: {
            id: 1147567,
            name: 'The Weather Channel'
        }
 */
public class WebhookItem {
    private RoomMessage message;
    private RoomItem room;

    public WebhookItem() {
    }

    public WebhookItem(RoomMessage message, RoomItem room) {
        this.message = message;
        this.room = room;
    }

    public RoomMessage getMessage() {
        return message;
    }

    public void setMessage(RoomMessage message) {
        this.message = message;
    }

    public RoomItem getRoom() {
        return room;
    }

    public void setRoom(RoomItem room) {
        this.room = room;
    }
}
