package com.delawareugent.poc.ProductService;

import com.delawareugent.poc.ProductService.adapters.messaging.Channels;
import com.delawareugent.poc.ProductService.domain.Product;
import com.delawareugent.poc.ProductService.persistence.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.*;

@SpringBootApplication
@EnableBinding(Channels.class)
public class ProductServiceApplication {

	private static Logger logger = LoggerFactory.getLogger(ProductServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabasePopulation(ProductRepository productRepository) {
		return (args) ->{
			logger.info("Product Service has started up successfully");
			populateDB(productRepository);
			logger.info("Product Service is ready to roll!");
		};
	}

	private void populateDB( ProductRepository productRepository){
		logger.info("Attempting to populate DB for standalone service development");

		logger.info("...Creating mock data");
		List<String> options = Arrays.asList("Bike", "Guitar", "Plant", "Painting", "Skateboard", "Watch", "Radio");
		List<String> colors = Arrays.asList("Black", "White", "Red"," Blue", "Green", "Yellow", "Purple");
		Random rand = new Random();

		List<Product> products = new ArrayList<>();

		for(int i = 0; i < 10000; i++){
				Double price = 100 + Math.random()*100;
				String res = colors.get(rand.nextInt(colors.size())) + " " + options.get(rand.nextInt(options.size()));
				products.add(new Product(new UUID(0L, (long)(Math.random() * 100)%4), res, "Anybody interested in my " + res.toLowerCase() + "? Still in excellent condition!", price));
		}

		logger.info("...Saving mock data");
		productRepository.saveAll(products);
		logger.info("Finished populating DB for development purposes");
	}

    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedMethod("GET");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}