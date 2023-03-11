package com.optum.reactive;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoService4 {
	public static void main(String[] args) throws InterruptedException {

		Flux<String> flux1 = Flux.just("a", "b", "c");
		Flux<String> flux2 = Flux.just("d", "e", "f");
		Flux.concatDelayError(flux1, flux2).subscribe(System.out::println);

		System.out.println("-------CONCAT DELAY-----------");

		flux1 = Flux.just("a", "b", "c").delayElements(Duration.ofMillis(125));
		flux2 = Flux.just("d", "e", "f").delayElements(Duration.ofMillis(100));
		Flux.concat(flux1, flux2).subscribe(System.out::println);
		Thread.sleep(2000);
		
		System.out.println("-------MERGE DELAY-----------");
		Flux.merge(flux1, flux2).subscribe(System.out::println);
		Thread.sleep(2000);
		
		System.out.println("-------MERGE SEQUENTIAL DELAY-----------");
		Flux.mergeSequential(flux1, flux2).subscribe(System.out::println);
		
		Thread.sleep(10000);
	}

	public Flux<String> monoConcatWith() {
		Mono<String> aMono = Mono.just("A");
		Mono<String> bMono = Mono.just("B");

		return aMono.concatWith(bMono);
	}

	public Flux<String> monoMergeWith() {
		Mono<String> aMono = Mono.just("A");
		Mono<String> bMono = Mono.just("B");

		return aMono.mergeWith(bMono);
	}

}
