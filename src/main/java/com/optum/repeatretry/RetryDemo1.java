package com.optum.repeatretry;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class RetryDemo1 {
	public static void main(String[] args) {
		getNumbers()
		.retry(2)
		.subscribe(Util.subscriber());
	}

	public static Flux<Integer> getNumbers() {
		return Flux.range(1, 10).map(i -> i == 5 ? i / 0 : i).doOnComplete(() -> System.out.println("doOnComplete()"))
				.onErrorMap(ex -> new RuntimeException("exception " + ex));
	}
}
