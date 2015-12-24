package com.mteng.controller;

import com.mteng.controller.helper.ControllerHelper;
import com.mteng.model.Page;
import com.mteng.model.TSRespContainer;
import com.mteng.util.ServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/")
public class WebController {

    @Autowired
    ControllerHelper helper;

    private static Logger logger = Logger.getLogger(WebController.class.getName());

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String getPhone(
            @ModelAttribute("SpringWeb") Page page,
            ModelMap model
    ) {

        UriComponentsBuilder URI = helper.getURI(page.getPhoneNum(), page.getPage());
        logger.info(URI.build().toUriString());
        TSRespContainer container = helper.getTsRespContainer(URI.toUriString());
        helper.populateModelAttribute(model, page, URI, container);

        return "dashboard2";
    }

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public String getSample(
            ModelMap model
    ) {
        Page page = new Page();
        page.setPage("1");
        page.setPhoneNum(ServiceConstants.SAMPLENUMBER);

        UriComponentsBuilder URI = helper.getURI(page.getPhoneNum(), page.getPage());
        logger.info(URI.build().toUriString());
        TSRespContainer container = helper.getTsRespContainer(URI.toUriString());
        helper.populateModelAttribute(model, page, URI, container);

        return "dashboard2";
    }
}
