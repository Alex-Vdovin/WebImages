package com.avisto.webimages.controllers;

import com.avisto.webimages.model.Image;
import com.avisto.webimages.model.User;
import com.avisto.webimages.repositories.ImageRepository;
import com.avisto.webimages.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ImageRepository imageRepository;

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
        return "redirect:/user/createImage";
    }

    @GetMapping("/user/images")
    public String getUserImages(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateOfCreation"));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Image> userImages = userService.getUserByUsername(username).getUserImages();
        Page<Image> images = imageRepository.findByUser(userService.getUserByUsername(username), pageable);
        model.addAttribute("images", images.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", images.getTotalPages());
        model.addAttribute("totalElements", images.getTotalElements());
        model.addAttribute("size", size);
        model.addAttribute("user", userService.getUserByUsername(username));
        return "userImages";
    }

    @PostMapping("/user/images/delete")
    public String deleteUserImage(@RequestParam("imageId") Long imageId) {
        userService.deleteImage(imageId);
        return "redirect:/user/images";
    }

    @GetMapping("/user/createImage")
    public String createImage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.getUserByUsername(username));
        return "addImages";
    }

}
