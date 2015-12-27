package com.mteng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/")
public class LandingPageController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage(ModelMap model) {
        return "dashboard";
    }
}
