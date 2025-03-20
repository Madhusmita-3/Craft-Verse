package edu.rims.craft_verse.controllers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.rims.craft_verse.constant.CategoryStatus;
import edu.rims.craft_verse.constant.ProductStatus;
import edu.rims.craft_verse.constant.WidgetStatus;
import edu.rims.craft_verse.dto.ProductReponseDTO;
import edu.rims.craft_verse.dto.ProductReponseDTO.CategoryResponse;
import edu.rims.craft_verse.entity.Category;
import edu.rims.craft_verse.entity.Product;
import edu.rims.craft_verse.entity.Widget;
import edu.rims.craft_verse.repository.CategoryRepository;
import edu.rims.craft_verse.repository.ProductRepository;
import edu.rims.craft_verse.repository.WidgetRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WidgetRepository widgetRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/dashboard")
    String admin() {
        return "admin/dashboard";
    }

    @GetMapping("/order")
    String adminOrder() {
        return "admin/order";
    }

    @GetMapping("/customer")
    String adminCustomer() {
        return "admin/customer";
    }

    @GetMapping("/category")
    String category(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "admin/category";
    }

    @PostMapping("/category")
    public String categoryAdd(@ModelAttribute Category category,
            @RequestParam("categoryImage") MultipartFile file, @RequestParam("categoryHoverImage") MultipartFile file1)
            throws IOException {
        if (!file.isEmpty() && !file1.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String originalFilename1 = file1.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String extName1 = originalFilename1.substring(originalFilename1.lastIndexOf("."));
            String fileName = "upload_images/" + UUID.randomUUID().toString() + extName;
            String fileName1 = "upload_images/" + UUID.randomUUID().toString() + extName1;
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            FileOutputStream fileOutputStream1 = new FileOutputStream(fileName1);
            fileOutputStream.write(file.getBytes());
            fileOutputStream1.write(file1.getBytes());
            fileOutputStream.close();
            fileOutputStream1.close();
            category.setCategoryImageUrl(fileName);
            category.setCategoryHoverImageUrl(fileName1);

        }
        categoryRepository.save(category);
        return "redirect:/admin/category";
    }

    @PostMapping("/category/remove")
    public String removecategory(@RequestParam("id") String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        category.setCategoryStatus(CategoryStatus.INACTIVE);
        categoryRepository.save(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/category/images/{categoryId}")
    @ResponseBody
    public byte[] categoryImage(@PathVariable String categoryId) throws IOException {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        FileInputStream fileInputStream = new FileInputStream(category.getCategoryImageUrl());
        return fileInputStream.readAllBytes();
    }

    @GetMapping("/category/hoverimages/{categoryId}")
    @ResponseBody
    public byte[] categoryHoverImage(@PathVariable String categoryId) throws IOException {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        FileInputStream fileInputStream = new FileInputStream(category.getCategoryHoverImageUrl());
        return fileInputStream.readAllBytes();
    }

    @GetMapping("/product")
    String product(Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<Product> products = productRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "admin/product";
    }

    @PostMapping("/product")
    public String productAdd(@ModelAttribute Product product, @RequestParam("productImage") MultipartFile file,
            @RequestParam(required = false) String imageUrl)
            throws IOException {

        if (imageUrl != null) {
            product.setProductImageUrl(imageUrl);
        }

        if (!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));

            String fileName = "upload_images/" + UUID.randomUUID().toString() + extName;
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
            product.setProductImageUrl(fileName);

        }
        
        productRepository.save(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/product/remove")
    public String removeProduct(@RequestParam("id") String productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setProductStatus(ProductStatus.DISCONTINUED.toString());
        productRepository.save(product);

        return "redirect:/admin/product";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam String id, Model model) {
        Category category = categoryRepository.findById(id).orElseThrow();
        model.addAttribute("category", category);
        // System.out.println(category.getCategoryTitle());
        return "admin/edit";
    }

    @GetMapping("/widget")
    public String getWidgets(Model model) {
        model.addAttribute("widgets", widgetRepository.findAll());
        return "admin/widget";
    }

    @PostMapping("/widget/add")
    public String postMethodName(@RequestParam String widgetName, @RequestParam String widgetId,
            @RequestParam Integer sequence) {
        Widget widget = widgetRepository.findById(widgetId).orElse(new Widget());
        widget.setWidgetName(widgetName);
        widget.setSequence(sequence);
        widget.setUpdatedDate(LocalDateTime.now());
        widgetRepository.save(widget);
        return "redirect:/admin/widget";
    }

    @GetMapping("/widget/remove")
    public String removeWidget(@RequestParam("id") String widgetId) {
        Widget widget = widgetRepository.findById(widgetId).orElseThrow();
        widget.setWidgetStatus(WidgetStatus.INACTIVE);
        widgetRepository.save(widget);
        return "redirect:/admin/widget";
    }

    @GetMapping("/widget/edit")
    public String editWidget(@RequestParam("id") String widgetId, Model model) {
        Widget widget = widgetRepository.findById(widgetId).orElseThrow();
        model.addAttribute("widget", widget);
        model.addAttribute("widgets", widgetRepository.findAll());
        return "admin/widget";
    }

    @GetMapping(value = "/image/{categoryId}", produces = { "images/jpg", "images/jpeg", "images/png" })
    @ResponseBody
    public byte[] getImages(@PathVariable String categoryId) throws IOException {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        String categoryImageUrl = category.getCategoryImageUrl();
        if (categoryImageUrl == null || categoryImageUrl.startsWith("http")) {
            return null;

        }
        FileInputStream fileInputStream = new FileInputStream(categoryImageUrl);

        return fileInputStream.readAllBytes();
    }

    @PostMapping("/widget/product/add")
    public String addProductToWidget(@RequestParam MultipartFile file) {

        if (file.isEmpty())
            return "redirect:/admin/widget";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            Map<String, String> details = new HashMap<>();

            // for header
            bufferedReader.readLine();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                processDetails(split[0], split[1]);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin/widget";
    }

    @GetMapping("/widget/products")
    public String getMethodName(@RequestParam("id") String wigetId, Model model) {
        Widget widget = widgetRepository.findById(wigetId).orElseThrow();
        model.addAttribute("widget", widget);
        return "admin/widget-product";
    }

    private void processDetails(String widgetId, String productId) {
        Product product = productRepository.findById(productId).orElse(null);
        Widget widget = widgetRepository.findById(widgetId).orElse(null);

        if (product != null && widget != null) {
            if (!widget.getProducts().contains(product)) {
                widget.addProduct(product);
                widgetRepository.save(widget);
            }

        }
    }

    @GetMapping("/widget/product/remove")
    public String getMethodName(@RequestParam String widgetId, @RequestParam String productId) {
        Widget widget = widgetRepository.findById(widgetId).orElseThrow();

        widget.removeProduct(productId);

        widgetRepository.save(widget);
        return "redirect:/admin/widget";
    }

    @GetMapping("/product/images/{productId}")
    @ResponseBody
    public byte[] productImage(@PathVariable String productId) throws IOException {
        Product product = productRepository.findById(productId).orElseThrow();
        FileInputStream fis = new FileInputStream(product.getProductImageUrl());

        return fis.readAllBytes();
    }

    @GetMapping("/products/{productId}")
    @ResponseBody
    public ProductReponseDTO getProduct(@PathVariable String productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        ProductReponseDTO dto = new ProductReponseDTO();
        dto.setProductId(productId);
        dto.setProductTitle(product.getProductTitle());
        dto.setProductDescription(product.getProductDescription());
        dto.setProductPrice(product.getProductPrice());
        dto.setProductStatus(product.getProductStatus());
        dto.setProductStockQuantity(product.getProductStockQuantity());
        dto.setProductImageUrl(product.getProductImageUrl());
        
        CategoryResponse category = dto.new CategoryResponse();
        category.setCategoryId(product.getCategory().getCategoryId());
        category.setCategoryTitle(product.getCategory().getCategoryTitle());
        dto.setCategory(category);

        return dto;
    }
}
