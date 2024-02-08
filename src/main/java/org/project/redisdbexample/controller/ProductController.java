package org.project.redisdbexample.controller;

import lombok.RequiredArgsConstructor;
import org.project.redisdbexample.entity.Product;
import org.project.redisdbexample.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        productRepository.findAll().forEach(allProducts::add);
        return allProducts;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productRepository.findById(String.valueOf(id)).orElse(null);
    }

    @PutMapping("/{id}")
    public Product updateById(@PathVariable Long id,
                              @RequestBody Product newProduct) {
        Optional<Product> existingProduct
                = productRepository.findById(String.valueOf(id));

        if (existingProduct.isPresent()) {
            Product updatedProduct
                    = existingProduct.get();

            updatedProduct.setName(newProduct.getName());
            updatedProduct.setPrice(newProduct.getPrice());
            updatedProduct.setQuantity(newProduct.getQuantity());

            productRepository.deleteById(String.valueOf(id));
            return productRepository.save(updatedProduct);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productRepository.delete(getById(id));
    }
}
