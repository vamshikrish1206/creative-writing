package com.project.createivewriting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping(path = "/")
    public String home(Model model){
        model.addAttribute("message","vamsh's");
        return "index";
    }

}
