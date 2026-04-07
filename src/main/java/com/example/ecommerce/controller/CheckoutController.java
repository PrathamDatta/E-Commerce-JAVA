package com.example.ecommerce.controller;

import com.example.ecommerce.model.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final OrderRepository orderRepository;

    public CheckoutController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String viewCheckout(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("cart", cart);
        return "checkout";
    }

    @PostMapping
    public String processCheckout(
            @RequestParam String customerName,
            @RequestParam String customerEmail,
            @RequestParam String shippingAddress,
            HttpSession session) {
        
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getItems().isEmpty()) {
            Order order = new Order();
            order.setCustomerName(customerName);
            order.setCustomerEmail(customerEmail);
            order.setShippingAddress(shippingAddress);
            order.setTotalAmount(cart.getTotal());

            for (var cartItem : cart.getItems()) {
                OrderItem orderItem = new OrderItem(cartItem.getProduct(), cartItem.getQuantity(), cartItem.getProduct().getPrice());
                order.getItems().add(orderItem);
            }
            orderRepository.save(order);
            cart.clear();
        }
        return "redirect:/";
    }

    @PostMapping("/ajax")
    @org.springframework.web.bind.annotation.ResponseBody
    public String processCheckoutAjax(
            @RequestParam String customerName,
            @RequestParam String customerEmail,
            @RequestParam String shippingAddress,
            HttpSession session) {
        
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getItems().isEmpty()) {
            Order order = new Order();
            order.setCustomerName(customerName);
            order.setCustomerEmail(customerEmail);
            order.setShippingAddress(shippingAddress);
            order.setTotalAmount(cart.getTotal());

            for (var cartItem : cart.getItems()) {
                OrderItem orderItem = new OrderItem(cartItem.getProduct(), cartItem.getQuantity(), cartItem.getProduct().getPrice());
                order.getItems().add(orderItem);
            }
            orderRepository.save(order);
            cart.clear();
            return "Success";
        }
        return "Empty";
    }
}
