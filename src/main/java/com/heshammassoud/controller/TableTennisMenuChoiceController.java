package com.heshammassoud.controller;

import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import com.heshammassoud.models.ActionResponse;
import com.heshammassoud.models.ActionTargetRequest;
import com.heshammassoud.service.CtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nonnull;

import static com.heshammassoud.models.ActionResponse.ofError;

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
    @ResponseBody
    @PostMapping(path = "/table-tennis-menu", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse choose(@RequestBody @Nonnull final ActionTargetRequest actionTargetRequest) {
        return ofError("Damn what a bad choice");
    }

    @AuthorizeJwtHeader
    @PostMapping(path = "/table-tennis-menu-list")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void list() {
        ctService.listTtOptions();
    }

}