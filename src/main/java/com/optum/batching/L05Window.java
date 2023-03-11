package com.optum.batching;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class L05Window {

	private static AtomicInteger atomicInt = new AtomicInteger(1);

	public static void main(String[] args) {
		eventStream().window(5)
		.flatMap(flux -> saveEvents(flux))
		.subscribe(Util.subscriber());

		Util.sleepSeconds(30);
	}

	private static Flux<String> eventStream() {
		return Flux.interval(Duration.ofMillis(500)).map(i -> "event" + i);
	}

	private static Mono<Integer> saveEvents(Flux<String> flux) {
		return flux.doOnNext(e -> System.out.println("Saving event " + e))
				.doOnComplete(() -> System.out.println("saved this batch"))
				.then(Mono.just(atomicInt.getAndIncrement()));
	}
}
