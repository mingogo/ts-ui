package com.mteng.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mteng.model.Phone;
import com.mteng.model.TSRespContainer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.logging.Logger;


@Controller
@RequestMapping(value = "/")
public class PhoneController {

    //TODO: Add logger
    private static Logger logger = Logger.getLogger(PhoneController.class.getName());

    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    public ModelAndView phone() {
        return new ModelAndView("phone", "command", new Phone());
    }

    @RequestMapping(value = "/phonesubmit", method = RequestMethod.POST)
    public String phoneSubmit(
            @ModelAttribute("SpringWeb") Phone phone,
            ModelMap model
    ) {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder URI = UriComponentsBuilder.newInstance();

        URI.queryParam("page", "1");
        URI.queryParam("size", "10");
        URI.scheme("http").host("www.mteng-ts-api.elasticbeanstalk.com").path("/api/v1/number/" + phone.getPhoneNum()).build();

        logger.info(URI.build().toUriString());

        String resp = restTemplate.getForObject(URI.toUriString(), String.class);
        ObjectMapper mapper = new ObjectMapper();
        TSRespContainer container = new TSRespContainer();

        try {
            container = mapper.readValue(resp, TSRespContainer.class);
        } catch (IOException e) {
            //TODO: Handle this exception
            e.printStackTrace();
        }

        model.addAttribute("number", phone.getPhoneNum());
        model.addAttribute("count", container.getCount());
        model.addAttribute("URI", URI.build());
        return "phoneResult";
    }
}
