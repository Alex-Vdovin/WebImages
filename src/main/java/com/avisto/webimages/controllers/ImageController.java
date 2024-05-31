package com.avisto.webimages.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {
    @GetMapping("/")
    public String images(){
        return "images";
    }
}
