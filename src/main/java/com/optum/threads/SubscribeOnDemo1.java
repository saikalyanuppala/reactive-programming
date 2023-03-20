package com.optum.threads;

import java.util.List;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOnDemo1 {

	public static Flux<String> with_subscribeOn() {

		List<String> list1 = List.of("shiva", "narayana", "bramha");
		List<String> list2 = List.of("durga", "laxmi", "saraswathi");

		var flux1 = flux1(list1).subscribeOn(Schedulers.boundedElastic()).log();
		var flux2 = flux1(list2).subscribeOn(Schedulers.boundedElastic()).log();

		return Flux.merge(flux1, flux2);
	}

	public static Flux<String> flux1(List<String> names) {
		return Flux.fromIterable(names).map(e -> upperCase(e));
	}

	public static String upperCase(String name) {
		Util.sleepSeconds(1);
		return name.toUpperCase();
	}

	public static void main(String[] args) {
		with_subscribeOn().subscribe(Util.subscriber());

		Util.sleepSeconds(5);
	}
}
