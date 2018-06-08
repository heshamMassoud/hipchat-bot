package com.heshammassoud.service;

import com.atlassian.adf.Document;
import com.atlassian.adf.block.codeblock.Language;
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
     *
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
                                                           .send(buildRichFormattedMessage())
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

    private Document buildRichFormattedMessage() {
        return Document.create().paragraph(p -> p
                .text("Here is some ")
                .strong("bold test")
                .text(" and ")
                .em("text in italics")
                .text(" as well as ")
                .link(" a link", "https://www.atlassian.com")
                .text(" , emojis ")
                .emoji(":smile:")
                .emoji(":rofl:")
                .emoji(":nerd:")
                .text(" and some code: ")
                .code("var i = 0;")
                .text(" and a bullet list"))
                       .bulletList(b -> b
                               .item("With one bullet point")
                               .item("And another"))
                       .info(p -> p
                               .paragraph("and an info panel with some text, with some more code below"))
                       .code(Language.javascript, "var i = 0;\nwhile(true) {\n  i++;\n}")
                       .paragraph("And a card")
                       .card("With a title", c -> c.attrs()
                                                   .link("https://www.atlassian.com")
                                                   .description("With some description, and a couple of attributes")
                                                   .background("https://www.atlassian.com")
                                                   .detail(d -> d
                                                           .title("Type")
                                                           .text("Task")
                                                           .icon("https://ecosystem.atlassian.net/secure/"
                                                                   + "viewavatar?size=xsmall&avatarId"
                                                                   + "=15318&avatarType=issuetype", "Task"))
                                                   .detail(d -> d
                                                           .title("User")
                                                           .text("Joe Blog")
                                                           .icon("https://ecosystem.atlassian.net/secure/"
                                                                   + "viewavatar?size=xsmall&avatarId="
                                                                   + "15318&avatarType=issuetype", "Task")));
    }

}
