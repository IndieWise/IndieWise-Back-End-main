package com.api.indiewise.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/indiewise.com.br")
    public String home() {
        return "login.html";
    }
}
