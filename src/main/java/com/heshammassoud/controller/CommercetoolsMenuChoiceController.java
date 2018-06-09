package com.heshammassoud.controller;

import com.atlassian.stride.model.context.UserContext;
import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import com.heshammassoud.models.ActionResponse;
import com.heshammassoud.service.CtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;

import static com.atlassian.stride.model.context.Context.user;
import static com.heshammassoud.models.ActionResponse.ofMessage;


@Controller
public class CommercetoolsMenuChoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommercetoolsMenuChoiceController.class);

    private final CtService ctService;

    public CommercetoolsMenuChoiceController(@Nonnull final CtService ctService) {
        this.ctService = ctService;
    }

    /**
     * korkogkerokgokergokor e.
     * @return gkorekgoekoger.
     */
    @ResponseBody
    @AuthorizeJwtHeader
    @PostMapping(path = "/commercetools-menu", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse choose(@RequestParam @Nonnull final String senderId,
                                 @RequestParam @Nonnull final String cloudId) {

        LOGGER.info("SenderId" + senderId);
        LOGGER.info("cloudId" + cloudId);

        final UserContext userContext = user(cloudId, senderId);
        LOGGER.info("Listing CT Options for " + userContext.toString());
        ctService.listCtOptions(userContext);
        return ofMessage("Awesome!");
    }

    /**
     * List all the CT playground options.
     * @param senderId send id.
     * @param cloudId cloud id.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/commercetools-menu-list")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void list(@RequestBody @Nonnull final String senderId, @RequestBody @Nonnull final String cloudId) {

        final UserContext userContext = user(cloudId, senderId);
        ctService.listCtOptions(userContext);
    }

}