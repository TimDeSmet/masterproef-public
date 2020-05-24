package com.delawareugent.poc.LogService.domain;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.dto.Point;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Measurement(name = "log")
public class Log {

    @Column(name = "time")
    private Instant time;

    @Column(name = "origin")
    private String serviceId;

    @Column(name = "description")
    private String description;

    public Log(){}

    public Log(Instant time, String serviceId, String description) {
        this.time = time;
        this.serviceId = serviceId;
        this.description = description;
    }

    public Point createPoint(){
        return Point.measurement("log")
                .time(time.toEpochMilli(), TimeUnit.MILLISECONDS)
                .addField("origin", serviceId)
                .addField("description", description)
                .build();
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
