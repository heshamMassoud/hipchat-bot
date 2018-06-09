package com.heshammassoud.controller;

import com.atlassian.stride.model.context.UserContext;
import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import com.heshammassoud.models.NextAction;
import com.heshammassoud.models.Target;
import com.heshammassoud.service.CtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nonnull;

import static com.atlassian.stride.model.context.Context.user;


@Controller
public class CommercetoolsMenuChoiceController {

    private final CtService ctService;

    public CommercetoolsMenuChoiceController(@Nonnull final CtService ctService) {
        this.ctService = ctService;
    }

    /**
     * korkogkerokgokergokor e.
     * @return gkorekgoekoger.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/commercetools-menu", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView choose() {
        return new ModelAndView("action-response")
                .addObject("message", "Awesome!")
                .addObject("nextAction", new NextAction(new Target("commercetoolsMenuList")));
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