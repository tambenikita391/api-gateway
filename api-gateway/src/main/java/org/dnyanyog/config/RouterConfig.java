package org.dnyanyog.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

		// Routing rule definitions
		return builder.routes()
				.route("auth_public_route", r -> r.path("/public/auth/**").uri("http://localhost:8081"))

				.route("auth_route", r -> r.path("/auth/user/**").uri("http://localhost:8081"))

				.route("employee_route", r -> r.path("/employee/**").uri("http://localhost:8082"))

				.build();

	}
}
