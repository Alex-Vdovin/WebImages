package com.avisto.webimages.controllers;

import com.avisto.webimages.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("users", userService.userList());
        return "admin";
    }
}
