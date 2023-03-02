package com.optum.mono;

import java.util.concurrent.Callable;

import com.optum.util.Util;

import reactor.core.publisher.Mono;

public class MonoFromSupplier {

	public static String getName() {
		System.out.println("Generating name...");
		return Util.faker().name().fullName();
	}

	public static void main(String[] args) {
		// even there is no subscriber getName() is invoked.
		// don't use this , use from supplier
		Mono.just(getName());
		System.out.println("---- from supplier ---");
		Mono<String> fromSupplier = Mono.fromSupplier(MonoFromSupplier::getName);

		// when you subscribe only getName() is invoked
		fromSupplier.subscribe(Util.onNext());

		System.out.println("-------- from callable ---");
		Callable<String> callable = () -> getName();
		Mono<String> fromCallable = Mono.fromCallable(callable);
		fromCallable.subscribe(Util.onNext());
	}
}
