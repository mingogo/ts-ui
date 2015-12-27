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
        // return "jumbo";
        return "dashboard";
    }

    @RequestMapping(value = "/jumbo", method = RequestMethod.GET)
    public String junbo2Page(ModelMap model) {
        return "jumbo";
    }
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String ajaxPage(ModelMap model) {
        return "dashboard";
    }
}
