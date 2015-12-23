package com.mteng.controller;

import com.mteng.model.Phone;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/")
public class LandingPageController {

//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public String printWelcome(ModelMap model) {
//        model.addAttribute("message", "Spring 3 MVC Hello World");
//        return "hello";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage(ModelMap model) {

        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "index";
    }

    @RequestMapping(value = "/jumbo", method = RequestMethod.GET)
    public String landingJumbo(ModelMap model) {

        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "jumbo";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String resultPage(ModelMap model) {

        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "dashboard";
    }

//    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
//    public ModelAndView hello(@PathVariable("name") String name) {
//
//        ModelAndView model = new ModelAndView();
//        model.setViewName("hello");
//        model.addObject("msg", name);
//
//        return model;
//    }

//    @RequestMapping(value = "/student", method = RequestMethod.GET)
//    public ModelAndView student() {
//        return new ModelAndView("student", "command", new Student());
//    }


//    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
//    public String addStudent(
//            @ModelAttribute("SpringWeb") Student student,
//            ModelMap model
//    ) {
//        model.addAttribute("name", student.getName());
//        model.addAttribute("age", student.getAge());
//        model.addAttribute("id", student.getId());
//
//        return "result";
//    }
}
