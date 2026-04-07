package com.example.ecommerce.config;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;

@Configuration
public class SeedDataConfig {

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, CategoryRepository categoryRepository) {
        return args -> {
            if (categoryRepository.count() == 0) {
                Category peripherals = new Category("Peripherals");
                Category displays = new Category("Displays");
                Category audio = new Category("Audio");
                Category accessories = new Category("Accessories");
                categoryRepository.saveAll(List.of(peripherals, displays, audio, accessories));

                Product p1 = new Product("Premium Headphones", "Noise-cancelling over-ear headphones.", new BigDecimal("299.99"), "/images/headphones.png", audio);
                Product p2 = new Product("Mechanical Keyboard", "High-profile mechanical keyboard with fast tactile switches.", new BigDecimal("149.50"), "/images/keyboard.png", peripherals);
                Product p3 = new Product("Gaming Mouse", "Precision gaming mouse with rgb lights.", new BigDecimal("79.99"), "/images/mouse.png", peripherals);
                Product p4 = new Product("4K Monitor", "27 inch 4K resolution monitor for crystal clear display.", new BigDecimal("399.00"), "/images/monitor.png", displays);
                Product p5 = new Product("Curved Ultrawide", "34 inch ultrawide immersive gaming display.", new BigDecimal("650.00"), "/images/ultrawide.png", displays);
                Product p6 = new Product("Desk Mat", "Large waterproof desk mat.", new BigDecimal("25.00"), "/images/deskmat.png", accessories);
                Product p7 = new Product("Wireless Earbuds", "True wireless sleek earbuds.", new BigDecimal("150.00"), "/images/earbuds.png", audio);

                productRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7));
            }
        };
    }
}
