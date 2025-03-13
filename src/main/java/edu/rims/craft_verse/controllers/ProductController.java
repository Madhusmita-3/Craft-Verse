package edu.rims.craft_verse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import edu.rims.craft_verse.entity.Category;
import edu.rims.craft_verse.repository.CategoryRepository;
import edu.rims.craft_verse.repository.ProductRepository;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/customer")
public class ProductController {

    @Autowired
    private CategoryRepository categoryRepository;
    // @Autowired
    // private ProductRepository productRepository;

    @GetMapping("/home")
    String customerHome(Model model) {

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "customer/home";
    }

    @GetMapping("/category/plp")
    String getProductByCategoryId(@RequestParam("id") String categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        model.addAttribute("category", category);
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
