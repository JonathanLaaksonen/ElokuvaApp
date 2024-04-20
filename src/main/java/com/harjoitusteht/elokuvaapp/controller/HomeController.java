package com.harjoitusteht.elokuvaapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/home"})  // Käsittelee sekä juuripolun että /home-polun
    public String home() {
        return "home";
    }
}