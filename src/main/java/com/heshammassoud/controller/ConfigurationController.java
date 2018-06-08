package com.heshammassoud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Nonnull;
import java.time.LocalTime;

@Controller
public class ConfigurationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);

    /**
     * Defines the descriptor endpoint for describing the bot json.
     *
     * @return a {@link ModelAndView} containing the appName and the serviceConfig.
     */
    @GetMapping(path = "configuration")
    public String configuration(@Nonnull final Model model) {
        model.addAttribute("msg", "A message from the controller");
        model.addAttribute("time", LocalTime.now());
        return "configuration";
    }

}