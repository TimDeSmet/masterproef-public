package com.delawareugent.poc.LogService.persistence;

import com.delawareugent.poc.LogService.domain.Log;
import org.influxdb.dto.BatchPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogRepository {

    private final InfluxDBWrapper influxDBWrapper;

    @Autowired
    public LogRepository(InfluxDBWrapper influxDBWrapper){
        this.influxDBWrapper = influxDBWrapper;
    }


    public BatchPoints getBatchPoints() {
        return influxDBWrapper.getBatchPoints();
    }

    public void write(BatchPoints batchPoints) {
        influxDBWrapper.write(batchPoints);
    }

    public List<Log> getLogsForQuery(String query) {
        return influxDBWrapper.getLogsForQuery(query);
    }
}
