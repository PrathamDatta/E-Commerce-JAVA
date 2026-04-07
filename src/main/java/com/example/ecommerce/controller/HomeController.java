package com.example.ecommerce.controller;

import com.example.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String search,
            Model model) {
        model.addAttribute("products", productService.getProducts(categoryId, search));
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("currentCategoryId", categoryId);
        model.addAttribute("currentSearch", search);
        return "index";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }
}
