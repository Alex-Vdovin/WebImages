package com.avisto.webimages.controllers;

import com.avisto.webimages.model.User;
import com.avisto.webimages.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String createUser(User user){
        userService.createUser(user);
        return "redirect:/login";
    }
    @GetMapping("/hello")
    public String securityUrl(@PathVariable("user") User user, Model model){
        model.addAttribute("user", user);
        return "hello";
    }
}
