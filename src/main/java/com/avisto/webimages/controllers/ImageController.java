package com.avisto.webimages.controllers;

import com.avisto.webimages.model.Image;
import com.avisto.webimages.repositories.ImageRepository;
import com.avisto.webimages.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayInputStream;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;
    private final UserService userService;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("imageName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

    @GetMapping("/")
    public String getAllImages(Model model) {
        List<Image> images = imageRepository.findAll().stream().sorted(Comparator.comparing(Image::getDateOfCreation).reversed()).toList();
        model.addAttribute("images", images);
        model.addAttribute("user", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("isAdmin", userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).isAdmin());
        return "all-images";
    }

}
