package com.delawareugent.poc.LogService.persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDBConfiguration {

    @Value("${spring.influxdb.url}")
    private String url;

    @Value("${spring.influxdb.database}")
    private String database;

    @Value("${spring.influxdb.user}")
    private String user;

    @Value("${spring.influxdb.password}")
    private String password;

    @Bean
    public InfluxDBWrapper influxDB(){
        String retentionPolicyName = "policy_remove_two_month";
        return new InfluxDBWrapper(retentionPolicyName, url, database, user, password);
    }
}
