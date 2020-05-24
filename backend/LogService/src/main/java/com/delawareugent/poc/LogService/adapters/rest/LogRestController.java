package com.delawareugent.poc.LogService.adapters.rest;

import com.delawareugent.poc.LogService.domain.Log;
import com.delawareugent.poc.LogService.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/logs")
public class LogRestController {

    private final LogService logService;

    @Autowired
    public LogRestController(LogService logService){
        this.logService = logService;
    }

    @GetMapping
    public List<Log> getLogs() {
        return logService.getLogs();
    }
}
