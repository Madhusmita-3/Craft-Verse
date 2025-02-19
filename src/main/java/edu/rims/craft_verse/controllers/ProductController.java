package edu.rims.craft_verse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.rims.craft_verse.entity.Category;

import edu.rims.craft_verse.repository.CategoryRepository;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/customer")
public class ProductController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/home")
    String customerHome(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories",categories);
        return "customer/home";
    }   
}