package edu.rims.craft_verse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.rims.craft_verse.repository.CategoryRepository;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/customer")
public class ProductController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/home")
    String customerHome(Model model) {

        return "customer/home";
    }

    @GetMapping("/plp")
    String customerPlp() {
        return "customer/plp";
    }

    @GetMapping("/pdp")
    String customerPdp() {
        return "customer/pdp";
    }

    @GetMapping("/signup")
    String customerSignup() {
        return "customer/signup";
    }

    @GetMapping("/login")
    String customerLogin() {
        return "customer/login";
    }

}