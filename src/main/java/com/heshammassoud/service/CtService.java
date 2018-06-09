package com.heshammassoud.service;

import com.atlassian.adf.Document;
import com.atlassian.adf.block.codeblock.Language;
import com.atlassian.adf.inline.Mark;
import com.atlassian.stride.api.StrideClient;
import com.atlassian.stride.model.context.UserContext;
import com.atlassian.stride.model.webhooks.MessageSent;
import com.heshammassoud.models.ActionGroupAction;
import com.heshammassoud.models.ActionGroupActionField;
import com.heshammassoud.models.ActionGroupParameters;
import com.heshammassoud.models.ActionTarget;
import com.heshammassoud.models.InlineExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

import static com.atlassian.stride.model.context.Context.user;
import static java.util.Collections.singletonList;

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

        final Map<String, String> params = new HashMap<>();
        params.put("cloudId", cloudId);
        params.put("senderId", senderId);

        strideClient.user()
                    .get()
                    .from(userContext)
                    .thenApply(userDetail -> buildMainMenuMessage(userDetail.getDisplayName(), params))
                    .thenCompose(document -> strideClient.message().send(document).toUser(userContext))
                    .thenCompose(userDetail -> strideClient.message().send(document2).toUser(userContext))
                    .thenAccept(response -> LOGGER.info(response.toString()))
                    .exceptionally(exception -> {
                        LOGGER.error("", exception);
                        return null;
                    });
    }

    /**
     * jfijrijiejgirege.
     * @param userName gjirjeigjerig
     * @return gjerigireg.
     */
    public static Document buildMainMenuMessage(@Nonnull final String userName,
                                                @Nonnull final Map<String, String> parameters) {
        /*final Mark commercetoolsMenu = createActionMark("commercetools menu", "commercetoolsMenu");
        final Mark tableTennisMenu = createActionMark("table tennis menu", "tableTennisMenu");*/

        final ActionGroupAction ctAction = createActionGroupAction("ct-menu", "commercetools Playground",
                "primary", "commercetoolsMenu", parameters);
        final ActionGroupAction ttAction = createActionGroupAction("tt-menu", "Table Tennis!",
                "default", "tableTennisMenu", parameters);

        final InlineExtension mainMenuActionGroup =
                createInLineMessageAction("mainMenu", ctAction, ttAction);

        return Document.create()
                       .paragraph(p -> p.text("Hi, ")
                                        .strong(userName)
                                        .text("!"))
                       .paragraph(paragraph -> paragraph.text(
                               "Please choose one of the following options:"))
                       .paragraph(p -> p.children(singletonList(mainMenuActionGroup)));
    }

    @Nonnull
    private static ActionGroupAction createActionGroupAction(
            @Nonnull final String key,
            @Nonnull final String title,
            @Nonnull final String appearance,
            @Nonnull final String targetKey,
            @Nonnull final Map<String, String> parameters) {

        final ActionGroupActionField actionTarget = new ActionGroupActionField(new ActionTarget(targetKey), parameters);
        return new ActionGroupAction(key, title, appearance, actionTarget);
    }

    @Nonnull
    private static InlineExtension createInLineMessageAction(@Nonnull final String actionGroupKey,
                                                               @Nonnull final ActionGroupAction... actions) {
        InlineExtension inlineExtension = new InlineExtension();
        inlineExtension.anyAttribute("extensionType", "com.atlassian.stride");
        inlineExtension.anyAttribute("extensionKey", "actionGroup");
        inlineExtension.anyAttribute("parameters", new ActionGroupParameters(actionGroupKey, actions));
        return inlineExtension;
    }


    private static Mark createActionMark(@Nonnull final String title, @Nonnull final String targetKey) {
        return Mark.mark("action")
                   .attribute("title", title)
                   .anyAttribute("target", new ActionTarget(targetKey));
    }

    private Document buildMainMenuMessage2(@Nonnull final String userName) {
        return Document.create()
                       .paragraph(p -> p.text("Hello, ")
                                        .strong(userName)
                                        .text("!"))
                       .orderedList(l -> l
                               .item(i -> i.paragraph("Play around with commercetools project data."))
                               .item(i -> i.paragraph("Play table tennis.")));
    }

    private Document buildRichFormattedMessage() {
        return Document.create()
                       .paragraph(p -> p
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

    /**
     * jrogerjgiejriogjieorger gre geroij geirj ioerg.
     *
     * @param userContext jirgioerj.
     */
    public void listCtOptions(@Nonnull final UserContext userContext,
                              @Nonnull final Map<String, String> parameters) {
        final ActionGroupAction viewProducts = createActionGroupAction("view-products", "View Products",
                "primary", "viewProducts", parameters);
        final ActionGroupAction viewProductTypes= createActionGroupAction("view-product-types", "View Product Types",
                "primary", "viewProductTypes", parameters);
        final ActionGroupAction viewInventories = createActionGroupAction("view-inventories", "View Inventory Entries",
                "primary", "viewInventories", parameters);

        final ActionGroupAction deleteProducts = createActionGroupAction("delete-products", "Delete Products",
                "primary", "deleteProducts", parameters);
        final ActionGroupAction deleteProductTypes= createActionGroupAction("delete-product-types", "Delete Product Types",
                "primary", "deleteProductTypes", parameters);
        final ActionGroupAction deleteInventories = createActionGroupAction("delete-inventories", "Delete Inventory Entries",
                "primary", "deleteInventories", parameters);

        final ActionGroupAction syncProducts = createActionGroupAction("sync-products", "Sync Products to another project",
                "primary", "syncProducts", parameters);
        final ActionGroupAction syncProductTypes= createActionGroupAction("sync-product-types", "Sync Product Types to another project",
                "primary", "syncProductTypes", parameters);
        final ActionGroupAction syncInventories = createActionGroupAction("sync-inventories", "Sync Inventory Entries to another project",
                "primary", "syncInventories", parameters);

        final InlineExtension ctPlaygroundActionGroup =
                createInLineMessageAction("ct-playground-list",
                        viewProducts, viewProductTypes, viewInventories,
                        deleteProducts, deleteProductTypes, deleteInventories,
                        syncProducts, syncProductTypes, syncInventories);

        final Document ctpgDoc = Document.create()
                                            .paragraph(paragraph -> paragraph.text(
                                                    "Please choose one of the following options:"))
                                            .paragraph(p -> p.children(singletonList(ctPlaygroundActionGroup)));


        strideClient.message().send(ctpgDoc).toUser(userContext);
    }

    public void listTtOptions() {

    }
}
