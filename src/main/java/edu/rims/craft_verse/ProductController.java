package edu.rims.craft_verse;

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
    
    
    
}
