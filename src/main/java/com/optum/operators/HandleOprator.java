package com.optum.operators;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class HandleOprator {

	public static void main(String[] args) {
		// handle = filter + map

		Flux.range(1, 10).handle((num, synSink) -> {
			if (num % 2 == 0) // filter
				synSink.next(num);
			else
				synSink.next(num + "a"); // map
		}).subscribe(Util.subscriber());

		Flux.generate(syncsink -> {
			String country = Util.faker().country().name();
			syncsink.next(country);
		}).map(Object::toString).handle((country, syncSink) -> {
			syncSink.next(country);
			if (country.toLowerCase().equals("india"))
				syncSink.complete();
		}).subscribe(Util.subscriber());
	}
}
