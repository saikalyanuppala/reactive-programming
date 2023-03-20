package com.optum.repeatretry;

import java.time.Duration;

import com.optum.util.Util;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

public class RetryWhenDemo {
	public static void main(String[] args) {
		getNumbers()
				.retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(1)).filter(ex -> ex instanceof ArithmeticException)
						.onRetryExhaustedThrow((a, retrySignal) -> Exceptions.propagate(retrySignal.failure())))
				.subscribe(Util.subscriber());
	}

	public static Flux<Integer> getNumbers() {
		return Flux.range(1, 10).map(i -> i == 5 ? i / 0 : i).doOnComplete(() -> System.out.println("doOnComplete()"))
				.onErrorMap(ex -> new RuntimeException("exception " + ex));
	}
}
