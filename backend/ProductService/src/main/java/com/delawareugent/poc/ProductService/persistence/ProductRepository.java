package com.delawareugent.poc.ProductService.persistence;

import com.delawareugent.poc.ProductService.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByProductId(String productId);

    void deleteByProductId(String productId);

    void deleteByCustomerId(UUID customerId);

    List<Product> findAllByCustomerId(UUID customerId);
}
