package com.optum.flux;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class FluxGenerate {
	public static void main(String[] args) {
		// flunk synchronous sink next() should be called only once
		// consumer is invoked again and again based on downstream demand
		Flux.generate(synchronousSink -> {
			synchronousSink.next("emit object ");
			// synchronousSink.next("emit object "); // Gives error
		}).take(5).subscribe(Util.subscriber());

		System.out.println("---- flux generate emit until-----");
		Flux.generate(synchronousSink -> {
			String country = Util.faker().country().name();
			synchronousSink.next(country);
			if (country.toLowerCase().equals("india"))
				synchronousSink.complete();
		}).subscribe(Util.subscriber());

		System.out.println("--- flux generate with state ----");
		Flux.generate(() -> 1, (counter, sink) -> {
			String country = Util.faker().country().name();
			sink.next(country);
			if (counter >= 10 || country.toLowerCase().equals("india"))
				sink.complete();
			return counter + 1;
		}).subscribe(Util.subscriber());
	}
}
