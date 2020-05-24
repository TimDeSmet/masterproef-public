package com.delawareugent.poc.LogService;

import com.delawareugent.poc.LogService.adapters.messaging.Channels;
import com.delawareugent.poc.LogService.persistence.InfluxDBWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBinding(Channels.class)
public class LogServiceApplication {

	private static Logger logger = LoggerFactory.getLogger(LogServiceApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(LogServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateDatabase(InfluxDBWrapper influxDBWrapper) {
		return (args) ->{
			logger.info("Populating database");
		};
	}

}
