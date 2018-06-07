package com.heshammassoud.models.HipChatRequest;

import com.fasterxml.jackson.annotation.JsonAlias;

/*
    {
    event: 'room_message',
    item: {
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
    },
    webhook_id: 578829
}
     */
public class Webhook {
    private String event;
    private WebhookItem item;
    @JsonAlias("webhook_id")
    private String webhookId;

    public Webhook() {
    } // JPA only

    public Webhook(String event, WebhookItem item, String webhookId) {
        this.event = event;
        this.item = item;
        this.webhookId = webhookId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public WebhookItem getItem() {
        return item;
    }

    public void setItem(WebhookItem item) {
        this.item = item;
    }

    public String getWebhookId() {
        return webhookId;
    }

    public void setWebhookId(String webhookId) {
        this.webhookId = webhookId;
    }
}