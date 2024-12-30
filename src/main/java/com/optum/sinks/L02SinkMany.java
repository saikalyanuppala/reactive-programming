package com.optum.sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class L02SinkMany {
    public static void main(String[] args) {
        Sinks.Many<String> manySinks = Sinks.many().multicast().onBackpressureBuffer();
        Flux<String> manyFlux = manySinks.asFlux();
        manyFlux.subscribe(System.out::println);
        manyFlux.subscribe(System.out::println);

        manySinks.tryEmitNext("Hello Many1");
        manySinks.tryEmitNext("Hello Many2");
        manySinks.tryEmitNext("Hello Many3");

    }
}
