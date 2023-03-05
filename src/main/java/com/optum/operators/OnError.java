package com.optum.operators;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OnError {
	public static void main(String[] args) {
		System.out.println("------- onErrorRetun ---------");
		Flux.range(1, 10).log().map(i -> 10 / (5 - i)).onErrorReturn(-1).subscribe(Util.subscriber());

		System.out.println("------- onErrorReume---- ");
		Flux.range(1, 10).log().map(i -> 10 / (5 - i)).onErrorResume(e -> fallback()).subscribe(Util.subscriber());

		System.out.println("------- onErrorContinue---- ");
		Flux.range(1, 10).log().map(i -> 10 / (5 - i)).onErrorContinue((err, obj) -> {
		}).subscribe(Util.subscriber());
	}

	private static Mono<Integer> fallback() {
		return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
	}
}
