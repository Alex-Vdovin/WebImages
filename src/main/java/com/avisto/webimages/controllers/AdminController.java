package com.avisto.webimages.controllers;

import com.avisto.webimages.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @PostMapping("/admin/delete")
    public String deleteUserImage(@RequestParam("imageId") Long imageId) {
        userService.deleteImage(imageId);// Предполагается, что у вас есть метод saveUser для сохранения изменений
        return "redirect:/"; // Перенаправляем обратно на страницу со списком изображений
    }
}
