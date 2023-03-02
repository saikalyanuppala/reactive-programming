package com.optum.flux;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class FluxCreate {
	public static void main(String[] args) {

		Flux.create(fluxSink -> {
			String country;
			do {
				country = Util.faker().country().name();
				fluxSink.next(country);
			} while (!country.toLowerCase().equals("india"));
		}).subscribe(Util.subscriber());
	}
}
