package com.optum.parallelism;

import java.util.List;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

public class ParallelFluxDemo {
	static List<String> list1 = List.of("shiva", "narayana", "bramha");

	public static String upperCase(String name) {
		Util.sleepSeconds(1);
		return name.toUpperCase();
	}

	public static Flux<String> explore_parallel() {
		return Flux.fromIterable(list1).publishOn(Schedulers.parallel()).map(ParallelFluxDemo::upperCase);
	}

	public static ParallelFlux<String> explore_parallel_1() {
		return Flux.fromIterable(list1).parallel().runOn(Schedulers.parallel()).map(ParallelFluxDemo::upperCase).log();
	}
	

	public static Flux<String> explore_parallel_flatmap() {
		return Flux.fromIterable(list1)
				   .flatMap(name -> Mono.just(name).map(ParallelFluxDemo::upperCase).subscribeOn(Schedulers.parallel()))
				   .log();
	}
	
	public static Flux<String> explore_parallel_flatmapsequential() {
		return Flux.fromIterable(list1)
				   .flatMapSequential(name -> Mono.just(name).map(ParallelFluxDemo::upperCase).subscribeOn(Schedulers.parallel()))
				   .log();
	}

	public static void main(String[] args) {
//		explore_parallel().subscribe(Util.subscriber());
		System.out.println("Observe thread names and time taken------------");
//		explore_parallel_1().subscribe(Util.subscriber());
//		explore_parallel_flatmap().subscribe(Util.subscriber());
		explore_parallel_flatmapsequential().subscribe(Util.subscriber());

		Util.sleepSeconds(10);
	}
}
