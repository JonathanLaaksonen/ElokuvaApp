package com.harjoitusteht.elokuvaapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.harjoitusteht.elokuvaapp.model.User;
import com.harjoitusteht.elokuvaapp.service.UserService;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
    return "register";
}


    @PostMapping("/register")
    public String registerUserAccount(User user) {
        userService.registerNewUserAccount(user);
        return "redirect:/login";
    }
}