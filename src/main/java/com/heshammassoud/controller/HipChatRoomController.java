package com.heshammassoud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HipChatRoomController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
