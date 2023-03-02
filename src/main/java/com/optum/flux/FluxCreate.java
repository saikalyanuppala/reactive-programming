package com.optum.flux;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class FluxCreate {
	public static void fluxCreate() {
		// on fluxSink you can call next any number of times
		Flux.create(fluxSink -> {
			for (int i = 1; i <= 5; i++)
				fluxSink.next("emit object " + i);
			fluxSink.complete();
		}).subscribe(Util.subscriber());
	}

	public static void main(String[] args) {
//consumer is invoked only once , can emit o to N elements
		Flux.create(fluxSink -> {
			String country;
			do {
				country = Util.faker().country().name();
				fluxSink.next(country);
			} while (!country.toLowerCase().equals("india"));
			fluxSink.complete();
		}).subscribe(Util.subscriber());

		System.out.println("---- check down stream is cancelled or not ---");

		Flux.create(fluxSink -> {
			String country;
			do {
				country = Util.faker().country().name();
				fluxSink.next(country);
			} while (!country.toLowerCase().equals("india") && !fluxSink.isCancelled());
			fluxSink.complete();
		}).take(3).subscribe(Util.subscriber());

		System.out.println("--- another flux create example -----");
		fluxCreate();
	}
}
