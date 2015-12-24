package com.mteng.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mteng.model.Page;
import com.mteng.model.Phone;
import com.mteng.model.TSRespContainer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.logging.Logger;


@Controller
@RequestMapping(value = "/")
public class PhoneController {

    private static Logger logger = Logger.getLogger(PhoneController.class.getName());

//    @RequestMapping(value = "/sample", method = RequestMethod.GET)
//    public String getSample(
//            ModelMap model
//    ) {
//        UriComponentsBuilder URI = UriComponentsBuilder.newInstance();
//
//        URI.queryParam("page", "1");
//        URI.queryParam("size", "100");
//        URI.scheme("http").host("www.mteng-ts-api.elasticbeanstalk.com").path("/api/v1/number/" + "3612321754").build();
//
//        //TODO: Add more info
//        logger.info(URI.build().toUriString());
//
//        TSRespContainer container = getTsRespContainer(URI);
//        String lastPage = container.getPagination().getLastPage();
//        String prevPage = container.getPagination().getPreviousPage();
//        String nextPage = container.getPagination().getNextPage();
//
//        model.addAttribute("entries", container.getCombinations());
//        model.addAttribute("number", "3612321754");
//        model.addAttribute("count", container.getCount());
//        model.addAttribute( "prev", "phonesubmit/".concat("3612321754").concat("/").concat("1"));
//        model.addAttribute("last", container.getCount());
//        model.addAttribute("next", container.getCount());
//
//        return "dashboard";
//    }

    @RequestMapping(value = "/phonesubmit", method = RequestMethod.POST)
    public String getPhone(
            @ModelAttribute("SpringWeb") Phone phone,
            ModelMap model
    ) {
        UriComponentsBuilder URI = getURI(phone);
        //TODO: Add more info in this logger
        logger.info(URI.build().toUriString());
        TSRespContainer container = getTsRespContainer(URI);

        model.addAttribute("number", phone.getPhoneNum());
        model.addAttribute("count", container.getCount());
        model.addAttribute("URI", URI.build());
        model.addAttribute("entries", container.getCombinations());

        return "dashboard";
    }

//    @RequestMapping(value = "/page", method = RequestMethod.POST)
//    public String getPage(
//            @ModelAttribute("SpringWeb") Page page,
//            ModelMap model
//    ) {
//        UriComponentsBuilder URI = UriComponentsBuilder.newInstance();
//
//        URI.scheme("http").host("www.mteng-ts-api.elasticbeanstalk.com").path("/api/v1/number/" + page.getPhoneNum()).build();
//        URI.queryParam("page", page.getPage());
//        URI.queryParam("size", "100");
//
//        TSRespContainer container = getTsRespContainer(URI);
//
//        model.addAttribute("number", page.getPhoneNum());
//        model.addAttribute("count", container.getCount());
//        model.addAttribute("URI", URI.build());
//        model.addAttribute("entries", container.getCombinations());
//
//        return "dashboard";
//    }

    private TSRespContainer getTsRespContainer(UriComponentsBuilder URI) {
        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject(URI.toUriString(), String.class);
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

    @RequestMapping(value = "/phonesubmit/{phoneNumber}/{pageNum}", method = RequestMethod.GET)
    public String getPhoneWithPage(
            @PathVariable String pageNum,
            @PathVariable String phoneNumber,
            ModelMap model
    ) {
        UriComponentsBuilder URI = getURI(phoneNumber, pageNum);
        //TODO: Add more info in this logger
        logger.info(URI.build().toUriString());
        TSRespContainer container = getTsRespContainer(URI);

        model.addAttribute("number", phoneNumber);
        model.addAttribute("count", container.getCount());
        model.addAttribute("URI", URI.build());
        model.addAttribute("entries", container.getCombinations());

        return "dashboard";
    }

    private UriComponentsBuilder getURI(@ModelAttribute("SpringWeb") Phone phone) {
        UriComponentsBuilder URI = UriComponentsBuilder.newInstance();

        URI.queryParam("page", "1");
        URI.queryParam("size", "100");
        URI.scheme("http").host("www.mteng-ts-api.elasticbeanstalk.com").path("/api/v1/number/" + phone.getPhoneNum()).build();
        return URI;
    }

    private UriComponentsBuilder getURI(String phoneNumber, String pageNum) {
        UriComponentsBuilder URI = UriComponentsBuilder.newInstance();

        URI.queryParam("page", pageNum);
        URI.queryParam("size", "100");
        URI.scheme("http").host("www.mteng-ts-api.elasticbeanstalk.com").path("/api/v1/number/" + phoneNumber).build();
        logger.info("the URI is " + URI.build().toUriString());
        return URI;
    }
}
