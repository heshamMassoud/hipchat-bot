package com.heshammassoud.controller;

import com.atlassian.stride.model.webhooks.MessageSent;
import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import com.heshammassoud.service.CtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;

@Controller
public class BotDirectMessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DescriptorController.class);

    private final CtService ctService;

    public BotDirectMessageController(@Nonnull final CtService ctService) {
        this.ctService = ctService;
    }

    @AuthorizeJwtHeader
    @PostMapping(path = "/bot-direct-message", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void mention(@RequestBody @Nonnull final MessageSent messageSent) {
        LOGGER.info("Got bot direct message callback with text {}", messageSent.getMessage().getText());
        ctService.reply(messageSent);
    }

}