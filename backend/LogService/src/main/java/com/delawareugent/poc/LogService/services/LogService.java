package com.delawareugent.poc.LogService.services;

import com.delawareugent.poc.LogService.domain.Log;
import com.delawareugent.poc.LogService.persistence.LogRepository;
import org.influxdb.dto.BatchPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository){
        this.logRepository = logRepository;
    }

    public void addToLogs(Log log){
        BatchPoints batchPoints = logRepository.getBatchPoints();
        batchPoints.point(log.createPoint());
        logRepository.write(batchPoints);
    }

    public List<Log> getLogs() {
        return logRepository.getLogsForQuery("SELECT * FROM log");
    }
}
