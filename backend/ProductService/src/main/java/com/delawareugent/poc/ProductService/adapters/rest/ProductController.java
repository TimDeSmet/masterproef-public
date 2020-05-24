package com.delawareugent.poc.ProductService.adapters.rest;

import com.delawareugent.poc.ProductService.ProductServiceApplication;
import com.delawareugent.poc.ProductService.domain.Product;
import com.delawareugent.poc.ProductService.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static Logger logger = LoggerFactory.getLogger(ProductServiceApplication.class);


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/normal")
    public Iterable<Product> getNormalProducts() {
        logger.info("Product Service received GET request for a normal amount of products");
        return productService.getNormalProducts();
    }

    @GetMapping("/performance")
    public Iterable<Product> getAllProducts() {
        logger.info("Product Service received GET request for all products");
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") String productId){
        logger.info("Product Service received GET request for product with id: " + productId);
        return productService.getProduct(productId);
    }

    //TODO customerID of person that makes call should be added
    @PostMapping
    public Iterable<Product> addProduct(@RequestBody Product product){
        logger.info("Product Service received POST request to create a product...");
        productService.addProduct(product);
        logger.info("...Creation was successful and has been published to Kafka");
        return productService.getAllProducts();
    }

    //TODO should only be able to delete own products
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") String productId){
        logger.info("Product Service received DELETE request to delete the product with ID: " + productId);
        productService.deleteProduct(productId);
        logger.info("...Deletion was successful and has been published to Kafka");
    }
}
