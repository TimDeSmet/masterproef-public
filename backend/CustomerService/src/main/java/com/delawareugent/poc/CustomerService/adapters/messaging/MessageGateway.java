package com.delawareugent.poc.CustomerService.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.UUID;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = Channels.CUSTOMER_REMOVED)
    void customerRemoved(UUID userId);
}
