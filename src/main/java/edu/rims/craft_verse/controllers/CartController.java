package edu.rims.craft_verse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/customer")

public class CartController {
    @GetMapping("/cart")
    String customerCart() {
        return "customer/cart";
    }
}
