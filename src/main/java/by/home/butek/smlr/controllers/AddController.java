package by.home.butek.smlr.controllers;

import by.home.butek.smlr.service.KeyMapperService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

@Controller
public class AddController {

    @Value("${smlr.prefix}")
    public String prefix;

    @Autowired
    public KeyMapperService service;

    @PostMapping(path = "add")
    @ResponseBody
    public ResponseEntity addRest(@RequestBody AddRequest request) {
        return ResponseEntity.ok(add(request.link));
    }

    @PostMapping(path = "addhtml")
    public String addHtml(Model model, @RequestParam(value = "link") String link) {
        AddResponse result = add(link);
        model.addAttribute("link", result.link);
        model.addAttribute("keyed", prefix + result.key);
        return "result";
    }

    private AddResponse add(String link) {
        return new AddResponse(link, service.add(link));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddRequest implements Serializable {
        private String link;
    }

    @Data
    @AllArgsConstructor
    public static class AddResponse implements Serializable {
        private String link;
        private String key;
    }

}
