package org.project.redisdbexample.repository;

import lombok.RequiredArgsConstructor;
import org.project.redisdbexample.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    public static final String HASH_KEY = "Product";
    @Autowired
    private RedisTemplate<String, Object> template;

    public Product save(Product product) {
        template.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Object> getAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public Product getById(Long id) {
        return (Product) template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteProduct(Long id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "Product with id:" + id + " deleted!";
    }
}
