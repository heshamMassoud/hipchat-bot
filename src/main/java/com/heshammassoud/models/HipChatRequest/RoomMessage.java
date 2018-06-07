package com.heshammassoud.models.HipChatRequest;

import io.evanwong.oss.hipchat.v2.users.UserItem;

/*
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
 */
public class RoomMessage {
    private String date;
    private UserItem from;
    private String id;
    private String message;
    private String type;

    public RoomMessage() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UserItem getFrom() {
        return from;
    }

    public RoomMessage(String date, UserItem from, String id, String message, String type) {
        this.date = date;
        this.from = from;
        this.id = id;
        this.message = message;
        this.type = type;
    }

    public void setFrom(UserItem from) {
        this.from = from;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
