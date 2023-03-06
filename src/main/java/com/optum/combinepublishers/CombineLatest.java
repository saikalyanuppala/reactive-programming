package com.optum.combinepublishers;

import java.time.Duration;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class CombineLatest {
	public static void main(String[] args) {

		Flux.combineLatest(getStrings(), getNumbers(), (s, i) -> s + i).subscribe(Util.subscriber());
		Util.sleepSeconds(10);
	}

	private static Flux<String> getStrings() {
		return Flux.just("a", "b", "c", "d").delayElements(Duration.ofSeconds(1));
	}

	private static Flux<Integer> getNumbers() {
		return Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(3));
	}
}
