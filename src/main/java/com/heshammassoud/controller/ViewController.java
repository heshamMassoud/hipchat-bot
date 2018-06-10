package com.heshammassoud.controller;

import com.atlassian.stride.model.webhooks.MessageSent;
import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import com.heshammassoud.service.stride.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;

import static com.heshammassoud.util.stride.ContextUtil.toUserContext;
import static com.heshammassoud.util.stride.MessageUtil.deleteMenu;

@Controller
public class ViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);

    private final MessageService messageService;

    public ViewController(@Nonnull final MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Whenever the view option in the main menu is chosen, this controller is called.
     * @param messageSent the message sent to the bot.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/view-menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void mention(@RequestBody @Nonnull final MessageSent messageSent) {

        LOGGER.info("Got view-menu callback with text {}", messageSent.getMessage().getText());
        messageService.sendPrivatley(toUserContext(messageSent), deleteMenu());
    }

}