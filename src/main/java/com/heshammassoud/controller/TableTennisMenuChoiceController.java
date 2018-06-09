package com.heshammassoud.controller;

import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import com.heshammassoud.models.NextAction;
import com.heshammassoud.models.Target;
import com.heshammassoud.service.CtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nonnull;

@Controller
public class TableTennisMenuChoiceController {


    private final CtService ctService;

    public TableTennisMenuChoiceController(@Nonnull final CtService ctService) {
        this.ctService = ctService;
    }

    /**
     * korkogkerokgokergokor e.
     * @return gkorekgoekoger.
     */
    @AuthorizeJwtHeader
    @PostMapping(path = "/table-tennis-menu", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView choose() {
        return new ModelAndView("action-response")
                .addObject("error", "Damn what a bad choice")
                .addObject("nextAction", new NextAction(new Target("tableTennisMenuList")));
    }

    @AuthorizeJwtHeader
    @PostMapping(path = "/table-tennis-menu-list")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void list() {
        ctService.listTtOptions();
    }

}