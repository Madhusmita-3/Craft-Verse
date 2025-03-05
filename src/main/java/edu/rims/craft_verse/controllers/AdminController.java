package edu.rims.craft_verse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/dashboard")
    String admin(){
        return "admin/dashboard";
    }
    
    @GetMapping("/category")
    String category(){
        return"admin/category";
    }
}
