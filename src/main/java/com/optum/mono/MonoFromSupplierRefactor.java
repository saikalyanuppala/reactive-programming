package com.optum.mono;

import com.optum.util.Util;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoFromSupplierRefactor {
	public static void main(String[] args) {

		getName(1);
		getName(2).subscribeOn(Schedulers.boundedElastic()).subscribe(Util.onNext(), Util.onError(), Util.onComplete());
		getName(3);

		// block internally subscribes and gives the result
		String name = getName(4).subscribeOn(Schedulers.boundedElastic()).block();
		System.out.println("name " + name);

		Util.sleepSeconds(4);
	}

	public static Mono<String> getName(int i) {
		System.out.println("enterted into getName() method.." + i);
		return Mono.fromSupplier(() -> {
			System.out.println("Generating name..");
			Util.sleepSeconds(3);
			return Util.faker().name().fullName();
		}).map(String::toUpperCase);
	}
}
