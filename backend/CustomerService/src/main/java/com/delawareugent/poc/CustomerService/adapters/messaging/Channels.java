package com.delawareugent.poc.CustomerService.adapters.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {

    String CUSTOMER_REMOVED = "customer_removed";

    @Output(CUSTOMER_REMOVED)
    MessageChannel userDeleted();
}
