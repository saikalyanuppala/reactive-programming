package com.optum.combinepublishers;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class Concat {
	public static void main(String[] args) {

		Flux<String> flux1 = Flux.just("a", "b");
		Flux<String> flux2 = Flux.just("c", "d", "e");
		Flux<String> flux3 = Flux.just("c", "d", "e");

		Flux<String> fluxConcat = flux1.concatWith(flux2);
		fluxConcat.subscribe(Util.subscriber());

		System.out.println("-------- another way concat----- ");
		fluxConcat = Flux.concat(flux1, flux2);
		fluxConcat.subscribe(Util.subscriber());

		System.out.println("-------- another way concat with erro ----- ");
		flux2 = Flux.error(new RuntimeException("ooops"));
		fluxConcat = Flux.concat(flux1, flux2, flux3);
		fluxConcat.subscribe(Util.subscriber());
		
		System.out.println("-------- another way concatDelayWithError with erro ----- ");
		fluxConcat = Flux.concatDelayError(flux1, flux2, flux3);
		fluxConcat.subscribe(Util.subscriber());

	}
}
