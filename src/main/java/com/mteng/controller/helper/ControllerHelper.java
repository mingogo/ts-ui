package com.mteng.controller.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mteng.model.Page;
import com.mteng.model.TSRespContainer;
import com.mteng.util.ServiceConstants;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.logging.Logger;
@Service
public class ControllerHelper {

    private static Logger logger = Logger.getLogger(ControllerHelper.class.getName());

    public UriComponentsBuilder getURI(String phoneNumber, String pageNum) {
        UriComponentsBuilder URI = UriComponentsBuilder.newInstance();

        URI.queryParam(ServiceConstants.PAGE, pageNum);
        URI.queryParam(ServiceConstants.SIZE, ServiceConstants.ENTRY_SET_SIZE);
        URI.scheme(ServiceConstants.API_SCHEME).host(ServiceConstants.API_HOST).
                path(ServiceConstants.API_PATH + phoneNumber).build();
        logger.info("URI is " + URI.build().toUriString());
        return URI;
    }

    public void populateModelAttribute(ModelMap model, Page page, UriComponentsBuilder URI, TSRespContainer container) {
        model.addAttribute("number", page.getPhoneNum());
        model.addAttribute("count", container.getCount());
        model.addAttribute("URI", URI.build());
        model.addAttribute("entries", container.getCombinations());
        model.addAttribute("prev", container.getPagination().getPreviousPage());
        model.addAttribute("next", container.getPagination().getNextPage());
        model.addAttribute("last", container.getPagination().getLastPage());
        model.addAttribute("currentPage", page.getPage());
    }

    public TSRespContainer getTsRespContainer(String uri) {
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
}
