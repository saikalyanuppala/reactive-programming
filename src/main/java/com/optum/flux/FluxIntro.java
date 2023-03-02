package com.optum.flux;

import java.util.List;
import java.util.stream.Stream;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class FluxIntro {
	public static void main(String[] args) {
		System.out.println("---- Flux integers ----");
		Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4);
		integerFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		System.out.println("----- Flux of Heterogeneous elements ---- ");
		Flux<Object> objectFlux = Flux.just(1, 2, 3, 'a', "sai", Util.faker().name().fullName());
		objectFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		System.out.println("----- Flux of even number elements ---- ");
		Flux<Integer> evenNumFlux = integerFlux.filter(e -> e % 2 == 0);
		evenNumFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		System.out.println("----- Flux of List ---- ");
		Flux<Integer> listFlux = Flux.fromIterable(List.of(10, 20, 30));
		listFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		System.out.println("----- Flux of Array ---- ");
		Flux<Integer> arrayFlux = Flux.fromArray(new Integer[] { 100, 200, 300 });
		arrayFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		System.out.println("----- Flux of Stream ---- ");
		Flux<Integer> streamFlux = Flux.fromStream(Stream.of(1000, 2000, 3000));
		streamFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
		streamFlux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		System.out.println("---- Flux of Range ----");
		Flux.range(3, 5).subscribe(Util.onNext());
        Flux.range(1, 5).map(i -> Util.faker().name().fullName()).subscribe(Util.onNext());
	}
}
