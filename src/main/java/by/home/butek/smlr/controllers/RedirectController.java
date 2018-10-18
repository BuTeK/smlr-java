package by.home.butek.smlr.controllers;

import by.home.butek.smlr.service.KeyMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class RedirectController {

    private static final String HEADER_NAME = "Location";

    @Autowired
    KeyMapperService service;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/{key}")
    public void redirect(@PathVariable("key") String key, HttpServletResponse response) {
        String link =  service.getLink(key);
        if (link != null) {
            response.setHeader(HEADER_NAME, link);
            response.setStatus(302);
        } else {
            response.setStatus(404);
        }

    }

}
