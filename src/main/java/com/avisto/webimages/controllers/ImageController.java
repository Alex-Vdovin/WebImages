package com.avisto.webimages.controllers;

import com.avisto.webimages.model.Image;
import com.avisto.webimages.repositories.ImageRepository;
import com.avisto.webimages.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;
    private final UserService userService;
    @GetMapping("/")
    public String getAllImages(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateOfCreation"));
        Page<Image> images = imageRepository.findAll(pageable);
        model.addAttribute("images", images.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", images.getTotalPages());
        model.addAttribute("totalElements", images.getTotalElements());
        model.addAttribute("size", size);
        model.addAttribute("userService", userService);
        model.addAttribute("user", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("isAdmin", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).isAdmin());
        return "all-images";
    }

}
