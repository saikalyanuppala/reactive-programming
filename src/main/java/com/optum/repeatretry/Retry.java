package com.optum.repeatretry;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class Retry {

	public static void main(String[] args) {
		getNumbers().retry(2).subscribe(Util.subscriber());
	}

	private static Flux<Integer> getNumbers() {
		return Flux.range(1, 3).doOnSubscribe(s -> System.out.println("Subscribed"))
				.doOnComplete(() -> System.out.println("doOnComplete"))
				.map(i -> i / (Util.faker().random().nextInt(1, 5) < 3 ? 0 : 1))
				.doOnError(err -> System.out.println("doOnError"));
	}
}
