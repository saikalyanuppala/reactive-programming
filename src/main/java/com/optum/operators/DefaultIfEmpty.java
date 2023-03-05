package com.optum.operators;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class DefaultIfEmpty {
	public static void main(String[] args) {
		getOrderedNumbers()
		.filter(i -> i > 10)
		.defaultIfEmpty(-100)
		.subscribe(Util.subscriber());
	}

	private static Flux<Integer> getOrderedNumbers() {
		return Flux.range(0, 10);
	}
}
