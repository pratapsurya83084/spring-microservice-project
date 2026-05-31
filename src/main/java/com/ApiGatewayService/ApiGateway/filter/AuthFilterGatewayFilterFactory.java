//package com.ApiGatewayService.ApiGateway.filter;
//
//
////these package decides where is request is going or which route(path)






package com.ApiGatewayService.ApiGateway.filter;

import com.ApiGatewayService.ApiGateway.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Component;

@Component
public class AuthFilterGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthFilterGatewayFilterFactory.Config> {

    @Autowired
    private Validator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthFilterGatewayFilterFactory() {
        super(Config.class);
    }

    Logger log = LoggerFactory.getLogger(AuthFilterGatewayFilterFactory.class);


    @Override
    public GatewayFilter apply(Config config) {

//        System.out.println("AUTH FILTER EXECUTED");


        return (exchange, chain) -> {

            // check secured endpoints
            if (validator.predicate.test(exchange.getRequest())) {

                // check authorization header
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                     log.info("header not contains means unathorized User");
//                    throw new BadRequestException(
//                            "Authorization header is Missing",
//                            HttpStatus.UNAUTHORIZED);

                    System.out.println("TOKEN MISSING");
                    exchange.getResponse()
                            .setStatusCode(HttpStatus.UNAUTHORIZED);

                    return exchange.getResponse().setComplete();
                }

                String authHeader =
                        exchange.getRequest()
                                .getHeaders()
                                .getFirst(HttpHeaders.AUTHORIZATION);


                log.info("authorized user so protect other apis");

                String token = null;

                if (authHeader != null && authHeader.startsWith("Bearer ")) {

                    token = authHeader.substring(7);
                }

                try {

                    jwtUtil.validateToken(token);

                } catch (Exception e) {

                    System.out.println("INVALID TOKEN");


                    exchange.getResponse()
                            .setStatusCode(HttpStatus.UNAUTHORIZED);

                    return exchange.getResponse().setComplete();
                }
            }


            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}