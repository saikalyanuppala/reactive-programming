package com.optum.mono;

import com.optum.util.Util;

import reactor.core.publisher.Mono;

public class AllInOneMono {
	public static void main(String[] args) {

		System.out.println("---- Mono Empty ---------");
		Mono<String> emptyMono = Mono.empty();
		emptyMono.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		System.out.println("----- Mono Error ----------");
		Mono<String> errorMono = Mono.error(() -> new RuntimeException("Exception.."));
		errorMono.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		System.out.println("----- Mono Just ---------");
		Mono<String> justMono = Mono.just("sai sandra");
		justMono.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		System.out.println("----- Mono Supplier ----------");
		Mono<String> supplierMono = Mono.fromSupplier(() -> "mono from supplier");
		supplierMono.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		System.out.println("----- Mono Callable ----------");
		Mono<String> callableMono = Mono.fromCallable(() -> "mono from callable");
		callableMono.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

		System.out.println("----- Mono Runnable -------");
		// fromRunnable will executed in onComplete() method only
		Mono<String> ruunableMono = Mono.fromRunnable(() -> System.out.println("runnable from run()"));
		ruunableMono.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

	}
}