package com.roze.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class HelloHandler {
    //    public Mono<ServerResponse> helloHand() {
//        Flux<String> dataPublisher = Flux.just("Firoze", "Millat", "Mydul", "Imtiaze", "Rayhan");
//        Mono<ServerResponse> serverResponseMono = ServerResponse
//                .ok()
//                .body(dataPublisher, String.class);
//        return serverResponseMono;
//    }
    public Mono<ServerResponse> helloHand(ServerRequest serverRequest) {
        String name = serverRequest.pathVariable("yourName");
        String response = "Your name is: " + name;
        Mono<String> publisher = Mono.just(response);
        Mono<ServerResponse> finalResponse = ServerResponse.ok().body(publisher, String.class);
        return finalResponse;
    }

   public Mono<ServerResponse> hiHandler(ServerRequest serverRequest) {
        Flux<String> response = Flux.just("Firoze", "Millat", "Mydul");
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(response, String.class);
    }
}
