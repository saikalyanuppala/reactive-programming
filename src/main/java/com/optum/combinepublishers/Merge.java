package com.optum.combinepublishers;

import java.time.Duration;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class Merge {
	public static void main(String[] args) {

		Flux.merge(QuatarFlights.getFlights(), EmiratesFlights.getFlights(), AmericanFlights.getFlights())
				.subscribe(Util.subscriber());

		Util.sleepSeconds(10);
	}
}

class QuatarFlights {
	public static Flux<String> getFlights() {
		return Flux.range(1, Util.faker().random().nextInt(1, 5)).delayElements(Duration.ofSeconds(1))
				.map(i -> "Quater " + Util.faker().random().nextInt(1, 100))
				.filter(i -> Util.faker().random().nextBoolean());
	}
}

class EmiratesFlights {
	public static Flux<String> getFlights() {
		return Flux.range(1, Util.faker().random().nextInt(1, 5)).delayElements(Duration.ofSeconds(1))
				.map(i -> "Emirates " + Util.faker().random().nextInt(1, 100))
				.filter(i -> Util.faker().random().nextBoolean());
	}
}

class AmericanFlights {
	public static Flux<String> getFlights() {
		return Flux.range(1, Util.faker().random().nextInt(1, 5)).delayElements(Duration.ofSeconds(1))
				.map(i -> "American " + Util.faker().random().nextInt(1, 100))
				.filter(i -> Util.faker().random().nextBoolean());
	}
}