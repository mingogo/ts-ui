package com.mteng.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mteng.model.Page;
import com.mteng.model.TSRespContainer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/")
public class WebController {

    private static Logger logger = Logger.getLogger(WebController.class.getName());

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String getPhone(
            @ModelAttribute("SpringWeb") Page page,
            ModelMap model
    ) {

        UriComponentsBuilder URI = getURI(page.getPhoneNum(), page.getPage());

        logger.info(URI.build().toUriString());

        TSRespContainer container = getTsRespContainer(URI.toUriString());

        model.addAttribute("number", page.getPhoneNum());
        model.addAttribute("count", container.getCount());
        model.addAttribute("URI", URI.build());
        model.addAttribute("entries", container.getCombinations());
        model.addAttribute("prev", container.getPagination().getPreviousPage());
        model.addAttribute("next", container.getPagination().getNextPage());
        model.addAttribute("last", container.getPagination().getLastPage());
        model.addAttribute("currentPage", page.getPage());

        return "dashboard2";
    }

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public String getSample(
            ModelMap model
    ) {
        Page page = new Page();
        page.setPage("1");
        page.setPhoneNum("3612321754");

        UriComponentsBuilder URI = getURI(page.getPhoneNum(), page.getPage());

        logger.info(URI.build().toUriString());

        TSRespContainer container = getTsRespContainer(URI.toUriString());

        model.addAttribute("number", page.getPhoneNum());
        model.addAttribute("count", container.getCount());
        model.addAttribute("URI", URI.build());
        model.addAttribute("entries", container.getCombinations());
        model.addAttribute("prev", container.getPagination().getPreviousPage());
        model.addAttribute("next", container.getPagination().getNextPage());
        model.addAttribute("last", container.getPagination().getLastPage());
        model.addAttribute("currentPage", page.getPage());

        return "dashboard2";
    }

    private TSRespContainer getTsRespContainer(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        TSRespContainer container = new TSRespContainer();

        try {
            container = mapper.readValue(resp, TSRespContainer.class);
        } catch (IOException e) {
            //TODO: Handle this exception
            e.printStackTrace();
        }
        return container;
    }

    private UriComponentsBuilder getURI(String phoneNumber, String pageNum) {
        UriComponentsBuilder URI = UriComponentsBuilder.newInstance();

        URI.queryParam("page", pageNum);
        URI.queryParam("size", "10");
        URI.scheme("http").host("www.mteng-ts-api.elasticbeanstalk.com").path("/api/v1/number/" + phoneNumber).build();
        logger.info("the URI is " + URI.build().toUriString());
        return URI;
    }
}
