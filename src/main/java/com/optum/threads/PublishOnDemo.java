package com.optum.threads;

import java.util.List;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class PublishOnDemo {

	public static String upperCase(String name) {
		Util.sleepSeconds(1);
		return name.toUpperCase();
	}

	public static Flux<String> without_publishOn() {

		List<String> list1 = List.of("shiva", "narayana", "bramha");
		List<String> list2 = List.of("durga", "laxmi", "saraswathi");

		var flux1 = Flux.fromIterable(list1).map(PublishOnDemo::upperCase).log();
		var flux2 = Flux.fromIterable(list2).map(PublishOnDemo::upperCase).log();

		return Flux.merge(flux1, flux2);
	}

	public static Flux<String> with_publishOn() {

		List<String> list1 = List.of("shiva", "narayana", "bramha");
		List<String> list2 = List.of("durga", "laxmi", "saraswathi");

		var flux1 = Flux.fromIterable(list1).publishOn(Schedulers.parallel()).map(PublishOnDemo::upperCase).log();
		var flux2 = Flux.fromIterable(list2).publishOn(Schedulers.parallel()).map(PublishOnDemo::upperCase).log();

		return Flux.merge(flux1, flux2);
	}

	public static Flux<String> with_flatmap() {

		List<String> list1 = List.of("shiva", "narayana", "bramha");
		List<String> list2 = List.of("durga", "laxmi", "saraswathi");

		var flux1 = Flux.fromIterable(list1)
				.flatMap(name -> Mono.just(name).map(PublishOnDemo::upperCase).subscribeOn(Schedulers.parallel()))
				.log();
		var flux2 = Flux.fromIterable(list2)
				.flatMap(name -> Mono.just(name).map(PublishOnDemo::upperCase).subscribeOn(Schedulers.parallel()))
				.log();

		return Flux.merge(flux1, flux2);
	}

	public static void main(String[] args) {
//		without_publishOn().subscribe(Util.subscriber());

		System.out.println("---- observe thread names -------------");

//		with_publishOn().subscribe(Util.subscriber());

		with_flatmap().subscribe(Util.subscriber());

		Util.sleepMillis(6000);
	}

}
