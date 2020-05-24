package com.delawareugent.poc.ProductService.services;

import com.delawareugent.poc.ProductService.ProductServiceApplication;
import com.delawareugent.poc.ProductService.adapters.messaging.MessageGateway;
import com.delawareugent.poc.ProductService.domain.Product;
import com.delawareugent.poc.ProductService.persistence.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final MessageGateway messageGateway;
    private static Logger logger = LoggerFactory.getLogger(ProductServiceApplication.class);

    @Autowired
    public ProductService(ProductRepository productRepository, MessageGateway messageGateway){
        this.productRepository = productRepository;
        this.messageGateway = messageGateway;
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Iterable<Product> getNormalProducts() {
        return productRepository.findAll().subList(0,50);
    }

    public Product getProduct(String productId){
        return productRepository.findByProductId(productId);
    }

    public void addProduct(Product product){
        productRepository.save(product);
        messageGateway.productCreated(product);
    }

    public void deleteProduct(String productId){
        productRepository.deleteByProductId(productId);
        messageGateway.productRemoved(productId);
    }

    public void deleteAllProductsForCustomer(UUID customerId){
        List<Product> deleted = productRepository.findAllByCustomerId(customerId);
        productRepository.deleteByCustomerId(customerId);
        deleted.stream().forEach(product -> messageGateway.productRemoved(product.getProductId()));
    }

}
