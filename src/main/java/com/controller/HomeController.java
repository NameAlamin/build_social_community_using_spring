package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage(Model model){
        return "home";
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/403")
    public String _403(){
        return "access-denied";
    }
}
