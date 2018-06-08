package com.heshammassoud.service;

import com.atlassian.adf.Document;
import com.atlassian.stride.api.StrideClient;
import com.atlassian.stride.api.model.EntityCreatedResponse;
import com.atlassian.stride.model.context.ConversationContext;
import com.atlassian.stride.model.webhooks.Conversation;
import com.atlassian.stride.model.webhooks.MessageSent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

import static com.atlassian.stride.model.context.Context.conversation;

@Service
public class CtService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CtService.class);
    private final StrideClient strideClient;

    public CtService(@Nonnull final StrideClient strideClient) {
        this.strideClient = strideClient;
    }

    /**
     * TODO.
     * @param messageSent TODO.
     */
    public void reply(@Nonnull final MessageSent messageSent) {
        final Conversation conversation = messageSent.getConversation();
        final ConversationContext conversationContext =
                conversation(messageSent.getCloudId(), conversation.getId());

        LOGGER.info("Sending message to: " + conversation.getName());
        LOGGER.info("in conversation: " + conversation.toString());

        final Document document = Document.create()
                                    .paragraph(p -> p.text("Hello, ").strong(conversation.getName()).text("!"))
                                    .h1("Welcome to using CTinator")
                                    .paragraph(p -> p.text("Your wish is my command:"))
                                    .orderedList(l -> l
                                            .item(i -> i.paragraph("Play around with commercetools project data."))
                                            .item(i -> i.paragraph("Play table tennis.")));

        send(conversationContext, document);
    }

    /**
     * TODO.
     * @param conversationContext TODO.
     * @param document TODO.
     * @return TODO.
     */
    @Nonnull
    public CompletableFuture<EntityCreatedResponse> send(@Nonnull final ConversationContext conversationContext,
                                                         @Nonnull final Document document) {

        LOGGER.info("Sending message: " + document.text());
        return strideClient.message()
                           .send(document)
                           .toConversation(conversationContext);
    }

}
