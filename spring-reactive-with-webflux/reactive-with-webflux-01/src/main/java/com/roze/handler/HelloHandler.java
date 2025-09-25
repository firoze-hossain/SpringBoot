package com.roze.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class HelloHandler {
    public Mono<ServerResponse> helloHand() {
        Flux<String> dataPublisher = Flux.just("Firoze", "Millat", "Mydul", "Imtiaze", "Rayhan");
        Mono<ServerResponse> serverResponseMono = ServerResponse
                .ok()
                .body(dataPublisher, String.class);
        return serverResponseMono;
    }
}
