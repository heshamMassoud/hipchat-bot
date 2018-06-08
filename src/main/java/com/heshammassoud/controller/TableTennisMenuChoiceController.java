package com.heshammassoud.controller;

import com.atlassian.stride.spring.auth.AuthorizeJwtHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class TableTennisMenuChoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableTennisMenuChoiceController.class);

    public TableTennisMenuChoiceController() {
    }

    @AuthorizeJwtHeader
    @PostMapping(path = "/table-tennis-menu")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void choose() {
        LOGGER.info("Chose the table tennis menu controller");
    }

}