package com.harjoitusteht.elokuvaapp.controller;


import com.harjoitusteht.elokuvaapp.model.User;
import com.harjoitusteht.elokuvaapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;  // Add this import

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute User user) {
        userService.registerNewUserAccount(user);
        return "redirect:/login"; // Redirect to the login page after successful registration
    }
}