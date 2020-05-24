package com.delawareugent.poc.CustomerService;

import com.delawareugent.poc.CustomerService.adapters.messaging.Channels;
import com.delawareugent.poc.CustomerService.domain.Customer;
import com.delawareugent.poc.CustomerService.persistence.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableBinding(Channels.class)
public class CustomerServiceApplication {

	private static Logger logger = LoggerFactory.getLogger(CustomerServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabasePopulation(CustomerRepository customerRepository) {
		return (args) ->{
			logger.info("Customer Service has started up successfully");
			populateDB(customerRepository);
			logger.info("Customer Service is ready to roll!");
		};
	}

	private void populateDB(CustomerRepository customerRepository){
		logger.info("Attempting to populate DB for standalone service development");

		Customer c1 = new Customer(new UUID(0L, 0L), "Travis", "Robinson", "travis.robinson@example.com");
		Customer c2 = new Customer(new UUID(0L, 1L), "Ross", "Adams", "ross.adams@example.com");
		Customer c3 = new Customer(new UUID(0L, 2L),"Kristen", "Gardner", "kristen.gardner@example.com");
		Customer c4 = new Customer(new UUID(0L, 3L), "Audrey", "Peck", "audrey.peck@example.com");

		List<Customer> customers = Arrays.asList(c1, c2, c3, c4);
		customerRepository.saveAll(customers);
		logger.info("Finished populating DB for development purposes");
	}

}
