package com.heshammassoud.controller;

import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import com.heshammassoud.models.ActionResponse;
import com.heshammassoud.service.CtService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;

import static com.heshammassoud.models.ActionResponse.ofErrorNextAction;

@Controller
public class TableTennisMenuChoiceController {


    private final CtService ctService;

    public TableTennisMenuChoiceController(@Nonnull final CtService ctService) {
        this.ctService = ctService;
    }

    @AuthorizeJwtHeader
    @PostMapping(path = "/table-tennis-menu")
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse choose() {
        return ofErrorNextAction("Damn what a bad choice", "tableTennisMenuList");
    }

    @AuthorizeJwtHeader
    @PostMapping(path = "/table-tennis-menu-list")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void list() {
        ctService.listTtOptions();
    }

}