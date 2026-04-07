package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getProducts(Long categoryId, String search) {
        boolean hasCategory = categoryId != null;
        boolean hasSearch = StringUtils.hasText(search);

        if (hasCategory && hasSearch) {
            return productRepository.findByCategoryIdAndNameContainingIgnoreCase(categoryId, search);
        } else if (hasCategory) {
            return productRepository.findByCategoryId(categoryId);
        } else if (hasSearch) {
            return productRepository.findByNameContainingIgnoreCase(search);
        }
        return productRepository.findAll();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
