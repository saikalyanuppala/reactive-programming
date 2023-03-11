package com.optum.reactive;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoService5 {
	public static void main(String[] args) throws InterruptedException {
		Flux<String> flux1 = Flux.just("a", "b", "c");
		Flux<String> flux2 = Flux.just("d", "e", "f");

		Flux.zip(flux1, flux2).subscribe(System.out::println);

		System.out.println("------------------");
		Flux.zip(flux1, flux2, (a, b) -> a + b).subscribe(System.out::println);
		Thread.sleep(1000);

		System.out.println("------------------");
		flux1 = Flux.just("a", "b", "c").delayElements(Duration.ofMillis(500));
		Flux.zip(flux1, flux2, (a, b) -> a + b).subscribe(System.out::println);
		Thread.sleep(2000);

		System.out.println("------------------");
		Flux.zip(flux1, flux2).map(t -> t.getT1() + t.getT2()).subscribe(System.out::println);

		Thread.sleep(2000);
	}

	public Mono<String> monoZipWith() {
		Mono<String> aMono = Mono.just("A");
		Mono<String> bMono = Mono.just("B");

		return aMono.zipWith(bMono).map(tupple -> tupple.getT1() + tupple.getT2());
	}
}
