package com.delawareugent.poc.ProductService.adapters.messaging;

import com.delawareugent.poc.ProductService.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerCommandHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomerCommandHandler.class);
    private final ProductService productService;

    @Autowired
    public CustomerCommandHandler(ProductService productService) {
        this.productService = productService;
    }

    @StreamListener(Channels.CUSTOMER_REMOVED)
    public void customerRemoved(UUID userId){
        logger.info("Received message that a user has been deleted wit id: " + userId);
        productService.deleteAllProductsForCustomer(userId);
        logger.info("Removed all products that belonged to customer and published these products to Kafka");
    }
}
