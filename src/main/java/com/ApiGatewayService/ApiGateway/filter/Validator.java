package com.ApiGatewayService.ApiGateway.filter;

//import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;
import java.util.function.Predicate;

@Component
public class Validator {

    private final AntPathMatcher antPathMatcher =
            new AntPathMatcher();

    public static final List<String> endpoints = List.of(
            "/user/register-user",
            "/user/generate-token",
            "/user/validate-token/**"
    );

    public Predicate<ServerHttpRequest> predicate = request -> {

        String requestPath = request.getURI().getPath();

        return endpoints.stream()
                .noneMatch(uri ->
                        antPathMatcher.match(uri, requestPath));
    };
}