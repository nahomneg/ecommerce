package com.example.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("r1", r -> r.host("**")
//                        .and()
//                        .path("/product")
//                        .uri("http://localhost:9096/"))
//                .route(r -> r.host("**.baeldung.com")
//                        .and()
//                        .path("/myOtherRouting")
//                        .filters(f -> f.prefixPath("/myPrefix"))
//                        .uri("http://othersite.com"))
//                .build();
//    }
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

}
