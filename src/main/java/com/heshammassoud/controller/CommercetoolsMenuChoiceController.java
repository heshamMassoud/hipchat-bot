package com.heshammassoud.controller;

import com.atlassian.stride.model.context.UserContext;
import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import com.heshammassoud.models.ActionResponse;
import com.heshammassoud.service.CtService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;

import static com.atlassian.stride.model.context.Context.user;
import static com.heshammassoud.models.ActionResponse.ofSuccessNextAction;


@Controller
public class CommercetoolsMenuChoiceController {

    private final CtService ctService;

    public CommercetoolsMenuChoiceController(@Nonnull final CtService ctService) {
        this.ctService = ctService;
    }

    @AuthorizeJwtHeader
    @PostMapping(path = "/commercetools-menu")
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse choose() {
        return ofSuccessNextAction("Awesome!", "commercetoolsMenuList");
    }

    @AuthorizeJwtHeader
    @PostMapping(path = "/commercetools-menu-list")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void list(@RequestBody @Nonnull final String senderId, @RequestBody @Nonnull final String cloudId) {

        final UserContext userContext = user(cloudId, senderId);
        ctService.listCtOptions(userContext);
    }

}