package com.optum.operators;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class SwtichIfEmpty {
	public static void main(String[] args) {

		getNumbers()
		           .filter(i -> i > 10)
		           .switchIfEmpty(fallback())
		           .subscribe(Util.subscriber());
	}

	private static Flux<Integer> getNumbers() {
		return Flux.range(1, 10);
	}

	private static Flux<Integer> fallback() {
		return Flux.range(11, 10);
	}
}
