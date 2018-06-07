package com.heshammassoud.controller;

import com.heshammassoud.models.hipchatrequest.RoomMessage;
import com.heshammassoud.models.hipchatrequest.Webhook;
import com.heshammassoud.models.hipchatresponse.RoomMessageBuilder;
import io.evanwong.oss.hipchat.v2.rooms.MessageColor;
import io.evanwong.oss.hipchat.v2.users.UserItem;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;

import static com.heshammassoud.Messages.WELCOME_MESSAGE;
import static java.lang.String.format;

@RestController
public class HipChatRoomController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    /**
     * Listens to the webhook with id "" and key "" registered on the hipchat room. It listens to messages with patterns
     * "". Whenever a message with the pattern "" is sent in the hipchat room a POST request is sent to this controller
     * with the payload {@link Webhook} {@code webhook}.
     *
     * @param webhook the payload posted
     * @return a {@link com.heshammassoud.models.hipchatresponse.RoomMessage} as response which would be posted in the
     *         hipchat room as a message.
     */
    @PostMapping("/")
    public com.heshammassoud.models.hipchatresponse.RoomMessage webhookListener(
            @RequestBody @Nonnull final Webhook webhook) {

        final RoomMessage message = webhook.getItem().getMessage();
        final UserItem messageSender = message.getFrom();

        String messageContent = format(WELCOME_MESSAGE, messageSender.getMentionName());
        if (message.getMessage().toLowerCase().contains("sync".toLowerCase())) {
            messageContent = "Syncing...";
        }

        return RoomMessageBuilder.of()
                                 .color(MessageColor.GRAY.name())
                                 .message(messageContent)
                                 .messageFormat("html")
                                 .notify(true)
                                 .build();
    }
}
