package com.heshammassoud.service;

import com.atlassian.adf.Document;
import com.atlassian.stride.api.StrideClient;
import com.atlassian.stride.model.context.UserContext;
import com.atlassian.stride.model.webhooks.MessageSent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

import static com.atlassian.stride.model.context.Context.user;

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
        final String cloudId = messageSent.getCloudId();
        final String senderId = messageSent.getSender().getId();

        final UserContext userContext = user(cloudId, senderId);

        final Document document2 = Document.fromMarkdown(
                "Please specify your commercetools project credentials in my configuration.");

        strideClient.user()
                    .get()
                    .from(userContext)
                    .thenCompose(userDetail -> strideClient.message()
                                                           .send(buildMainMenuMessage(userDetail.getUserName()))
                                                           .toUser(userContext))
                    .thenCompose(userDetail -> strideClient.message()
                                                           .send(document2)
                                                           .toUser(userContext));


    }

    private Document buildMainMenuMessage(@Nonnull final String userName) {
        return Document.create()
                       .paragraph(p -> p.text("Hello, ").strong(userName).text("!"))
                       .h1("Welcome to using CTinator")
                       .paragraph(p -> p.text("Your wish is my command:"))
                       .orderedList(l -> l
                               .item(i -> i.paragraph("Play around with commercetools project data."))
                               .item(i -> i.paragraph("Play table tennis.")));
    }

}
