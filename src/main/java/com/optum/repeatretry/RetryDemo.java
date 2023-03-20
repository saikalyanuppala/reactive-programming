package com.optum.repeatretry;

import java.time.Duration;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

public class RetryDemo {

	public static void main(String[] args) {
		getNumbers().retry(2).subscribe(Util.subscriber());

		System.out.println("--- retry with fixed delay ----");
		getNumbers().retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))
		.subscribe(Util.subscriber());

		Util.sleepSeconds(30);
	}

	private static Flux<Integer> getNumbers() {
		return Flux.range(1, 3).doOnSubscribe(s -> System.out.println("Subscribed"))
				.doOnComplete(() -> System.out.println("doOnComplete"))
				.map(i -> i / (Util.faker().random().nextInt(1, 5) < 3 ? 0 : 1))
				.doOnError(err -> System.out.println("doOnError"));
	}
}
