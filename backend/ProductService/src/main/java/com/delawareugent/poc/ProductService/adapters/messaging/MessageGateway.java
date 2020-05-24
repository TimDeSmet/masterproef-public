package com.delawareugent.poc.ProductService.adapters.messaging;

import com.delawareugent.poc.ProductService.domain.Product;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = Channels.PRODUCT_CREATED)
    void productCreated(Product product);

    @Gateway(requestChannel = Channels.PRODUCT_REMOVED)
    void productRemoved(String productId);
}
