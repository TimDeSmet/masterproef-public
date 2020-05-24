package com.delawareugent.poc.LogService.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    String CUSTOMER_REMOVED = "customer_removed";
    String PRODUCT_CREATED = "product_created";
    String PRODUCT_REMOVED = "product_removed";

    @Input(CUSTOMER_REMOVED)
    SubscribableChannel customerRemoved();

    @Input(PRODUCT_CREATED)
    SubscribableChannel productCreated();

    @Input(PRODUCT_REMOVED)
    SubscribableChannel productRemoved();

}
