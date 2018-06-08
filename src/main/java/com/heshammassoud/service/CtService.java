package com.heshammassoud.service;

import com.atlassian.adf.Document;
import com.atlassian.stride.api.StrideClient;
import com.atlassian.stride.model.context.ConversationContext;
import com.atlassian.stride.model.context.UserInConversationContext;
import com.atlassian.stride.model.webhooks.Conversation;
import com.atlassian.stride.model.webhooks.MessageSent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

import static com.atlassian.stride.model.context.Context.conversation;
import static com.atlassian.stride.model.context.Context.userInConversation;

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
        final String conversationId = conversation.getId();
        final String cloudId = messageSent.getCloudId();
        final String senderId = messageSent.getSender().getId();

        final ConversationContext conversationContext = conversation(cloudId, conversationId);
        final UserInConversationContext userContext = userInConversation(cloudId, conversationId, senderId);

        final Document document = Document.create()
                                          .paragraph(p -> p.text("Hello, ").strong(conversation.getName()).text("!"))
                                          .h1("Welcome to using CTinator")
                                          .paragraph(p -> p.text("Your wish is my command:"))
                                          .orderedList(l -> l
                                                  .item(i ->
                                                          i.paragraph("Play around with commercetools project data."))
                                                  .item(i -> i.paragraph("Play table tennis.")));

        final Document document2 = Document.fromMarkdown(
                "Please specify your commercetools project credentials in my configuration.");

        strideClient.user()
                    .get()
                    .from(userContext)
                    .thenCompose(userDetail -> {
                        LOGGER.info("Sending message to: " + userDetail.getDisplayName());
                        LOGGER.info("w/ username: " + userDetail.getUserName());
                        LOGGER.info("Sending message: " + document.text());
                        return strideClient.message()
                                           .send(document)
                                           .toConversation(conversationContext);
                    })
                    .thenCompose(userDetail -> strideClient.message()
                                                           .send(document2)
                                                           .toConversation(conversationContext));


    }

}