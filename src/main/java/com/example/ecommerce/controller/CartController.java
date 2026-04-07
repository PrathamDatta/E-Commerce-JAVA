package com.example.ecommerce.controller;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        model.addAttribute("cart", getCart(session));
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, HttpSession session) {
        Optional<Product> productOpt = productService.getProductById(productId);
        if (productOpt.isPresent()) {
            Cart cart = getCart(session);
            cart.addItem(productOpt.get(), 1);
            session.setAttribute("cart", cart); 
        }
        return "redirect:/"; 
    }

    @PostMapping("/add-ajax")
    @ResponseBody
    public String addToCartAjax(@RequestParam Long productId, HttpSession session) {
        Optional<Product> productOpt = productService.getProductById(productId);
        if (productOpt.isPresent()) {
            Cart cart = getCart(session);
            cart.addItem(productOpt.get(), 1);
            session.setAttribute("cart", cart);
            return String.valueOf(cart.getItemCount());
        }
        return "0";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long productId, HttpSession session) {
        Cart cart = getCart(session);
        cart.removeItem(productId);
        return "redirect:/cart";
    }
}
