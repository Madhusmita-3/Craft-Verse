package edu.rims.craft_verse.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.rims.craft_verse.constant.WidgetStatus;
import edu.rims.craft_verse.entity.Category;
import edu.rims.craft_verse.entity.Widget;
import edu.rims.craft_verse.repository.CategoryRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategoryRepository categoryRepository;
    @GetMapping("/dashboard")
    String admin(){
        return "admin/dashboard";
    }
    
    @GetMapping("/category")
    String category(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return"admin/category";
    }
    @PostMapping("/category/save")
    String category(@ModelAttribute Category category){
        categoryRepository.save(category);
        return"redirect:/admin/category";
    }
    @GetMapping("/product")
    String product(){
        return"admin/product";
    }
    @GetMapping("/widget")
    public String getWidgets(Model model){
        model.addAttribute(attributeName:"widgets", WidgetRepository.findAll());
        return "admin/widget";
    }

    @PostMapping("/widget/add")
    public String postMethodName (@RequestParam String widgetName, @RequestParam String widgetId){
        Widget widget=new Widget();

        if (widget !=null && !widgetId.isEmpty()) {
            widget.setWidgetId(widgetId);
            
        }
        System.out.println("Value of:" + widgetId);
        widget.setWidgetName(widgetName);
        widget.setUpdatedDate(LocalDateTime.now());
        WidgetRepository.save(widget);
        return "redirect:/admin/widget";
    }

    @GetMapping("/widget/remove")
    public String removeWidget(@RequestParam("id") String widgetId){
        Widget widget= WidgetRepository.findById(widgetId).orElseThrow();
        widget.setWidgetstatus(WidgetStatus.INACTIVE);
        WidgetRepository.save(widget);
        return "redirect:/admin/widget";
    }
    @GetMapping("/widget/edit")
    public String editWidget(@RequestParam("id") String widgetId, Model model){
        Widget widget= WidgetRepository.findById(widgetId).orElseThrow();
        model.addAttribute(attributeName:"widget", widget);
        model.addAttribute(attributeName:"widgets", WidgetRepository.findAll());
        return "admin/widget";
    }
}
