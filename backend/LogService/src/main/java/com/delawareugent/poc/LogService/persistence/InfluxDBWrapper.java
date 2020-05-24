package com.delawareugent.poc.LogService.persistence;

import com.delawareugent.poc.LogService.domain.Log;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.Query;
import org.influxdb.impl.InfluxDBResultMapper;

import java.util.List;
import java.util.Objects;

public class InfluxDBWrapper {

    private InfluxDB influx;

    private String db;

    public InfluxDBWrapper(String retentionPolicyName, String url, String database, String user, String password) {
        Objects.requireNonNull(url, "Please provide an InfluxDB url");
        Objects.requireNonNull(database, "Please provide an InfluxDB database");
        Objects.requireNonNull(user, "Please provide an InfluxDB user");
        Objects.requireNonNull(password, "Please provide an InfluxDB password");

        this.db = database;

        influx = InfluxDBFactory.connect(url, user, password);

        query(new Query("CREATE DATABASE " + database));
        influx.setDatabase(database);

        query(new Query("CREATE RETENTION POLICY " + retentionPolicyName + " ON " + database + " DURATION 60d REPLICATION 2"));
        influx.setRetentionPolicy(retentionPolicyName);

        influx.enableBatch(BatchOptions.DEFAULTS.actions(2000).flushDuration(100));
    }

    public QueryResult query(String queryString) {
        Query query = new Query(queryString, db);
        return this.influx.query(query);
    }

    public QueryResult query(Query query) {
        return this.influx.query(query);
    }

    public void write(BatchPoints points) {
        this.influx.write(points);
    }

    public BatchPoints getBatchPoints() {
        return BatchPoints.database(db).build();
    }

    public List<Log> getLogsForQuery(String queryString){
        QueryResult queryResult = influx.query(new Query(queryString, db));
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Log.class);
    }
}
