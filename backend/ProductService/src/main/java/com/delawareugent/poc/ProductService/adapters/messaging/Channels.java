package com.delawareugent.poc.ProductService.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    String CUSTOMER_REMOVED = "customer_removed";
    String PRODUCT_CREATED = "product_created";
    String PRODUCT_REMOVED = "product_removed";

    @Input(CUSTOMER_REMOVED)
    SubscribableChannel customerRemoved();

    @Output(PRODUCT_CREATED)
    MessageChannel productCreated();

    @Output(PRODUCT_REMOVED)
    MessageChannel productRemoved();

}