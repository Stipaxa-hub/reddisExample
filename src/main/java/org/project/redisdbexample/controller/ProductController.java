package org.project.redisdbexample.controller;

import lombok.RequiredArgsConstructor;
import org.project.redisdbexample.entity.Product;
import org.project.redisdbexample.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    public List<Object> getAllProducts() {
        return productRepository.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productRepository.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return productRepository.deleteProduct(id);
    }
}
