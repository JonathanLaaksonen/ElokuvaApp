package com.harjoitusteht.elokuvaapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Assumes you have a login.html in your templates directory
    }

    // Assuming that you want to handle the logout confirmation page as well.
    @GetMapping("/logout-success")
    public String logout() {
        return "login"; // Redirects to the login page with a logout message
    }
}