package com.example.DemoSpringBootApplication.controller;

import com.example.DemoSpringBootApplication.entity.Product;
import com.example.DemoSpringBootApplication.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@Tag(name = "Product Application")
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Get all the products")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Get product by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found for ID: " + id);
        }
    }

    @Operation(summary = "Create and update products")
    @PostMapping
    public ResponseEntity<Collection<Product>> createOrUpdateProducts(@RequestBody Collection<Product> products) {
        Collection<Product> savedProducts = productService.createOrUpdateProducts(products);
        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
    }

   /* @Operation(summary = "Delete product by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
*/
   @Operation(summary = "Delete product by id")
   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
       boolean deleted = productService.deleteProduct(id);
       if (deleted) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found for ID: " + id);
       }
   }


    @Operation(summary = "Delete all products")
    @DeleteMapping
    public ResponseEntity<Void> deleteAllProducts() {
        productService.deleteAllProducts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
