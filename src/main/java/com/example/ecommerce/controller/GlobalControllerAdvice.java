package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpSession;
import com.example.ecommerce.model.Cart;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("cartItemCount")
    public int getCartItemCount(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            return cart.getItemCount();
        }
        return 0;
    }
}
