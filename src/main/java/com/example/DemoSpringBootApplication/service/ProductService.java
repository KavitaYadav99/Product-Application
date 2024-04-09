package com.example.DemoSpringBootApplication.service;

import com.example.DemoSpringBootApplication.entity.Product;

import java.util.Collection;
import java.util.List;

public interface ProductService {
        List<Product> getAllProducts();
        Product getProductById(Long id);
        // Product createOrUpdateProduct(Product product);
        Collection<Product> createOrUpdateProducts(Collection<Product> products);
        boolean deleteProduct(Long id);
        void deleteAllProducts();
}
