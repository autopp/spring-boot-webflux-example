package com.example.demo.controller;

import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@Value
class HelloResponse {
    private final String message;
}

@RestController
public class HelloController {
    @GetMapping("/greet")
    public Mono<HelloResponse> greet() {
        return Mono.just(new HelloResponse("hello"));
    }
}
