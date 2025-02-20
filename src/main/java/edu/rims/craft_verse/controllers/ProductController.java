package edu.rims.craft_verse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/customer")
public class ProductController {

    @GetMapping("/home")
    String customerHome() {
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
