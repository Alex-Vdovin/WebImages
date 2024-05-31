package com.avisto.webimages.controllers;

import com.avisto.webimages.model.User;
import com.avisto.webimages.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public String securityUrl(Model model){
        model.addAttribute("user", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "hello";
    }
    @PostMapping("/user/createImage")
    public String createImage(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        userService.saveImage(userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()), imageFile);
        return "redirect:/";
    }
    @GetMapping("/getUserImages")
    public String getUserImages(Model model){
        model.addAttribute("images", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUserImages());
        model.addAttribute("user", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "userImages";
    }
    @GetMapping("/")
    public String images(){
        return "addImages";
    }
}
