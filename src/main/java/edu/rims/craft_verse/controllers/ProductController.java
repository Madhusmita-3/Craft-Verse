package edu.rims.craft_verse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.rims.craft_verse.constant.WidgetStatus;
import edu.rims.craft_verse.entity.Category;
import edu.rims.craft_verse.entity.Product;
import edu.rims.craft_verse.repository.CategoryRepository;
import edu.rims.craft_verse.repository.ProductRepository;
import edu.rims.craft_verse.repository.WidgetRepository;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/customer")
public class ProductController {

    private final CartController cartController;

    @Autowired
    private CategoryRepository categoryRepository;
    // @Autowired
    // private ProductRepository productRepository;

     @Autowired
    private WidgetRepository widgetRepository;

    @GetMapping("/home")
    String customerHome(Model model) {

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("widgets", widgetRepository.findByWidgetStatus(WidgetStatus.ACTIVE, Sort.by("sequence")));
        widgetRepository.findByWidgetStatus(WidgetStatus.ACTIVE, Sort.by("sequence")).forEach(w -> 
        System.out.println(w.getWidgetName()));
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

    // @GetMapping("/product/search")
    // String searchProduct(@RequestParam("search") String orderItemName) {
    //     System.out.println(orderItemName);
    //     return "customer/plp";
    // }

@GetMapping("/customer/search")
    String searchProduct(@RequestParam String search, Model model){
        List<Product> products = productRepository
            .findByProductTitleContainingIgnoreCaseAndProductStatus(search, ProductStatus.AVAILABLE);
        model.addAttribute("products", products);

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        
        return "customer/plp";
    }

    @GetMapping ("/product/search")
    String searchProduct(){
        return "customer/plp";
    }
}

}
