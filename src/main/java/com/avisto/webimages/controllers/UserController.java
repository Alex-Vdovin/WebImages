package com.avisto.webimages.controllers;

import com.avisto.webimages.model.Image;
import com.avisto.webimages.model.User;
import com.avisto.webimages.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @PostMapping("/user/createImage")
    public String createImage(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        userService.saveImage(userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()), imageFile);
        return "redirect:/user";
    }

    @GetMapping("/user/images")
    public String getUserImages(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Image> images = userService.getUserByUsername(username).getUserImages();
        model.addAttribute("images", images
                .stream()
                .sorted(Comparator
                        .comparing(Image::getDateOfCreation).reversed())
                .collect(Collectors.toList()));
        model.addAttribute("user", userService.getUserByUsername(username));
        return "userImages";
    }

    @PostMapping("/user/images/delete")
    public String deleteUserImage(@RequestParam("imageId") Long imageId) {
        userService.deleteImage(imageId);
        return "redirect:/user-images";
    }

    @GetMapping("/user")
    public String createImage() {
        return "addImages";
    }

}
