package com.optum.flux;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxMonoConversion {
	public static void main(String[] args) {

		System.out.println("---- Create Flux from Mono ----");
		Mono<String> mono = Mono.just("sai ram");
		Flux<String> flux = Flux.from(mono);
		flux.subscribe(Util.onNext());

		System.out.println("---- Create Mono from Flux ----");

		Flux<Integer> flux1 = Flux.range(1, 10).filter(e -> e > 5);
		Mono<Integer> mono1 = flux1.next();
		mono1.subscribe(Util.onNext());
	}
}
