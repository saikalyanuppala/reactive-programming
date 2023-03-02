package com.optum.mono;

import com.optum.util.Util;

import reactor.core.publisher.Mono;

public class MonoPractice {

	public static void main(String[] args) {

		// publisher
		Mono<String> mono = Mono.just("sai sandra").map(String::toUpperCase);

		// subscriber
		mono.subscribe(System.out::println, System.err::println, () -> System.out.println("Completed"));

		mono = Mono.just("sai sandra").map(String::toUpperCase).map(e -> null);
		mono.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
	}

}
