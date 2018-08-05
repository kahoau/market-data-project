package com.example.frontend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WelcomeController {

    // inject via application.properties
    @Value("${ajax.base.url}")
    private String ajaxBaseUrl;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("ajaxBaseUrl", this.ajaxBaseUrl);
        return "welcome";
    }

}