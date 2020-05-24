package com.delawareugent.poc.LogService.adapters.messaging;

import com.delawareugent.poc.LogService.LogServiceApplication;
import com.delawareugent.poc.LogService.domain.Log;
import com.delawareugent.poc.LogService.services.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CommandHandler {

    private static Logger logger = LoggerFactory.getLogger(LogServiceApplication.class);
    private final LogService logService;

    @Autowired
    public CommandHandler(LogService logService){
        this.logService = logService;
    }

    @StreamListener(Channels.CUSTOMER_REMOVED)
    public void logCustomerRemoved(Object obj){
        addLog("CustomerSerivce", "A customer has been removed: " + obj.toString());
    }

    @StreamListener(Channels.PRODUCT_CREATED)
    public void logProductCreated(Object obj){
        addLog("ProductService", "A product has been created: " + obj.toString());
    }

    @StreamListener(Channels.PRODUCT_REMOVED)
    public void logProductRemoved(Object obj){
        addLog("ProductService", "A product has been removed: " + obj.toString());
    }

    private void addLog(String serviceName, String message){
        Log log = new Log(Instant.now(), serviceName, message);
        logger.info("Received log from other service");
        logService.addToLogs(log);
        logger.info("Received log successfully stored");
    }
}
