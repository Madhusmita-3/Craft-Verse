package edu.rims.craft_verse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")

public class PaymentController {
    @GetMapping("/checkout")
    String customerCheckout() {
        return "customer/checkout";
    }

}
