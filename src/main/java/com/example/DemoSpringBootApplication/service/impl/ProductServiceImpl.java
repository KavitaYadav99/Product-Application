package com.example.DemoSpringBootApplication.service.impl;

import com.example.DemoSpringBootApplication.entity.Product;
import com.example.DemoSpringBootApplication.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private Map<Long, Product> productMap = new HashMap<>();
    private long id = 1;

    @Override
    public List<Product> getAllProducts() {
        return productMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        return productMap.get(id);
    }

    @Override
    public Collection<Product> createOrUpdateProducts(Collection<Product> products) {
        for (Product product : products) {
            if (product.getId() == null) {
                product.setId(id++);
            }
            productMap.put(product.getId(), product);
        }
        return products;
    }

   @Override
   public boolean deleteProduct(Long id) {
       return productMap.remove(id) != null;
   }


    @Override
    public void deleteAllProducts() {
        productMap.clear();
    }
}
