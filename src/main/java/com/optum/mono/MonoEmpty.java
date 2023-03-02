package com.optum.mono;

import com.optum.util.Util;

import reactor.core.publisher.Mono;

public class MonoEmpty {
	public static void main(String[] args) {

		System.out.println("----Has Data------");
		userRepository(1).subscribe(Util.onNext(), Util.onError(), Util.onComplete());
		System.out.println("---------Empty----------");
		userRepository(2).subscribe(Util.onNext(), Util.onError(), Util.onComplete());
		System.out.println("-----------Error---------");
		userRepository(3).subscribe(Util.onNext(), Util.onError(), Util.onComplete());
	}

	public static Mono<String> userRepository(Integer id) {
		if (id == 1)
			return Mono.just(Util.faker().name().fullName());
		else if (id == 2)
			return Mono.empty();
		else
			return Mono.error(new RuntimeException("Not in the allowed range"));
	}
}
