package com.delawareugent.poc.APIGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ApiGatewayApplication {

	private static Logger logger = LoggerFactory.getLogger(ApiGatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

 	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r -> r.host("*").and().path("/customers/**").uri("http://customer:2000"))
				.route(r -> r.host("*").and().path("/products/**").uri("http://product:2001"))
				.route(r -> r.host("*").and().path("/logs/**").uri("http://log-server:2002"))
				.build();
	}

	@Bean
	public GlobalFilter globalFilter() {
		return (exchange, chain) -> {
			String method = exchange.getRequest().getMethodValue();
			String url = exchange.getRequest().getPath().toString();
			String queryParams = exchange.getRequest().getQueryParams().toString();

			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				StringBuilder sb = new StringBuilder()
						.append('[').append(method).append(']')
						.append(' ').append(url)
						.append('?').append(queryParams);

				logger.info(sb.toString());
			}));
		};
	}
}
