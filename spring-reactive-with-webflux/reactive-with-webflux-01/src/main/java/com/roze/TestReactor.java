package com.roze;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class TestReactor {
    Flux<String> fooPublisher() {
        Flux<String> publisher = Flux.just("Firoze", "Millat", "Mydul").delayElements(Duration.ofSeconds(1));
        return publisher;
    }

    Mono<String> barPublisher() {
        Mono<String> monoPublisher = Mono.just("firoze@gmail.com");
        return monoPublisher;
    }

    public static void main(String[] args) throws InterruptedException {
//        Flux<String> publisher = new TestReactor().fooPublisher();
//        publisher.subscribe(str -> System.out.println(str));
//        publisher.subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });

//        Thread.sleep(10000);
        Mono<String> monoPub = new TestReactor().barPublisher();
        monoPub.subscribe((str) -> System.out.println(str));
    }
}
