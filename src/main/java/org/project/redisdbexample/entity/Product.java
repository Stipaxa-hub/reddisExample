package org.project.redisdbexample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@RedisHash("Product")
public class Product implements Serializable {
    @Id
    @Indexed
    private Long id;
    private String name;
    private Long quantity;
    private Long price;
}
