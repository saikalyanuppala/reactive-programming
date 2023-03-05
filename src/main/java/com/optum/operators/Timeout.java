package com.optum.operators;

import java.time.Duration;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class Timeout {
	public static void main(String[] args) {
//		practice();
		getOrderedNumbers().timeout(Duration.ofSeconds(2)).subscribe(Util.subscriber());

		getOrderedNumbers().timeout(Duration.ofSeconds(2), fallback()).subscribe(Util.subscriber());
		Util.sleepSeconds(60);
	}

	private static Flux<Integer> getOrderedNumbers() {
		return Flux.range(1, 10).delayElements(Duration.ofSeconds(5));
	}

	private static Flux<Integer> fallback() {
		return Flux.range(100, 10).delayElements(Duration.ofMillis(200));
	}

	public static void practice() {
		System.out.println("--- with generate-------");
		Flux.generate(synchronousSink -> {
			Integer num = getRandom();
			synchronousSink.next(num);
			if (num == 111)
				synchronousSink.complete();
		}).subscribe(Util.subscriber());

		System.out.println("--------- with create-----------");
		Flux.create(fluxSink -> {
			Integer num;
			do {
				num = getRandom();
				fluxSink.next(num);
			} while (!(num == 111));
		}).subscribe(Util.subscriber());

	}

	private static Integer getRandom() {
		return Util.faker().random().nextInt(100, 200);
	}
}
