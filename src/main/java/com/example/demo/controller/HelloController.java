package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


record GreetResponse(String message) {
}

record UpperRequest(List<String> words) {
}

record UpperResponse(List<String> words) {
}

@RestController
public class HelloController {
    @GetMapping("/greet")
    public Mono<GreetResponse> greet() {
        return Mono.just(new GreetResponse("hello"));
    }

    @PostMapping("/upper")
    public Mono<UpperResponse> upper(@RequestBody UpperRequest param) {
        return Flux.fromIterable(param.words()).map(String::toUpperCase).collectList().map(UpperResponse::new);
    }
}
