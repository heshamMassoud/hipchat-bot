package com.heshammassoud.models.hipchatrequest;

import io.evanwong.oss.hipchat.v2.users.UserItem;

import javax.annotation.Nonnull;

/**
 * * This is how the JSON representation of this bean looks like.
 * <code>
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
 * </code>
 **/
public class RoomMessage {
    private String date;
    private UserItem from;
    private String id;
    private String message;
    private String type;

    public RoomMessage() {
    }

    public RoomMessage(@Nonnull final String date, @Nonnull final UserItem from,
                       @Nonnull final String id, @Nonnull final String message, @Nonnull final String type) {
        this.date = date;
        this.from = from;
        this.id = id;
        this.message = message;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(@Nonnull final String date) {
        this.date = date;
    }

    public UserItem getFrom() {
        return from;
    }

    public void setFrom(@Nonnull final UserItem from) {
        this.from = from;
    }

    public String getId() {
        return id;
    }

    public void setId(@Nonnull final String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(@Nonnull final String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(@Nonnull final String type) {
        this.type = type;
    }
}
