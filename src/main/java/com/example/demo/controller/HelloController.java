package com.example.demo.controller;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Collections;

record Param(FilePart file) {
}

record Response(int answer) {
}

@RestController
public class HelloController {
    @PostMapping("/read")
    public Mono<Response> read(Param param) throws IOException {
        return param.file().content()
                .map(DataBuffer::asInputStream)
                .collectList()
                .flatMap(list -> {
                    try (final var is = new SequenceInputStream(Collections.enumeration(list))) {
                        // Use Input Stream
                        return Mono.just(new Response(42));
                    } catch (IOException ex) {
                        return Mono.error(ex);
                    }
                })
                ;
    }
}
