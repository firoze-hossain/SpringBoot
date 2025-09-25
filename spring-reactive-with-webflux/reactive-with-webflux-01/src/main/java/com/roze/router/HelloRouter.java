package com.roze.router;

import com.roze.handler.HelloHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HelloRouter {
    @Autowired
    private HelloHandler helloHandler;

    @Bean
    RouterFunction<ServerResponse> routerConfig() {
//        RequestPredicate request = RequestPredicates.GET("/hello");
//        HandlerFunction<ServerResponse> handlerFunction = new HandlerFunction<ServerResponse>() {
//            @Override
//            public Mono<ServerResponse> handle(ServerRequest request) {
//                //Flux<String> dataPublisher = Flux.just("Firoze", "Millat", "Mydul", "Imtiaze", "Rayhan").delayElements(Duration.ofSeconds(1));
//                Flux<String> dataPublisher = Flux.just("Firoze", "Millat", "Mydul", "Imtiaze", "Rayhan");
//                Mono<ServerResponse> serverResponseMono = ServerResponse
//                        .ok()
//                        //.contentType(MediaType.TEXT_EVENT_STREAM)
//                        .body(dataPublisher, String.class);
//                return serverResponseMono;
//            }
//        };
        //RouterFunctions.route(RequestPredicates.GET("/hello"),null);
//        RouterFunction<ServerResponse> routerFunction = RouterFunctions.route(RequestPredicates.GET("/hello"), response -> {
//            Flux<String> dataPublisher = Flux.just("Firoze", "Millat", "Mydul", "Imtiaze", "Rayhan");
//            Mono<ServerResponse> serverResponseMono = ServerResponse
//                    .ok()
//                    .body(dataPublisher, String.class);
//            return serverResponseMono;
//        });
//        RouterFunction<ServerResponse> routerFunction = RouterFunctions.route(RequestPredicates.GET("/hello"), request -> {
//            Mono<ServerResponse> serverResponseMono = helloHandler.helloHand();
//            return serverResponseMono;
//        });
//        return routerFunction;

//        return RouterFunctions.route(RequestPredicates.GET("/hello"), request -> {
//            Mono<ServerResponse> serverResponseMono = helloHandler.helloHand();
//            return serverResponseMono;
//        });

        //return RouterFunctions.route(RequestPredicates.GET("/hello"), request -> helloHandler.helloHand());
        // return RouterFunctions.route(RequestPredicates.GET("/hello/{yourName}"), request -> helloHandler.helloHand(request));
        //using method reference
        //return RouterFunctions.route(RequestPredicates.GET("/hello/{yourName}"), helloHandler::helloHand);
        return RouterFunctions
                .route(RequestPredicates.GET("/hello/{yourName}"), helloHandler::helloHand)
                .andRoute(RequestPredicates.GET("/hi"), helloHandler::hiHandler);
    }
}
