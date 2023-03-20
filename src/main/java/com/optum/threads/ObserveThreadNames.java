package com.optum.threads;

import java.time.Duration;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class ObserveThreadNames {

	public static void main(String[] args) {
		getNumbers_sync().subscribe(Util.subscriber());
		System.out.println("---------- Observe thread names when delay in publisher------------");
		getNumbers_indelay().subscribe(Util.subscriber());

		Util.sleepSeconds(8);
	}

	public static Flux<Integer> getNumbers_sync() {
		return Flux.range(1, 5).map(e -> e * 3);
	}

	public static Flux<Integer> getNumbers_indelay() {
		return Flux.range(1, 5).map(e -> e * 3).delayElements(Duration.ofSeconds(1));
	}
}
