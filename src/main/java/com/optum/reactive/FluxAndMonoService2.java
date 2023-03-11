package com.optum.reactive;

import java.util.List;
import java.util.function.Function;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoService2 {

	public static void main(String[] args) {

		namesFlux().subscribe(ele -> System.out.println("flux element " + ele));
		nameMono().subscribe(ele -> System.out.println("mono element " + ele));
		nameMono_Flatmap().subscribe(System.out::println);

		Flux<String> flux = nameMono_flatMapMany().flatMapMany(FluxAndMonoService2::spliName);
		flux.subscribe(e -> System.out.println(e));

		System.out.println("_________________________");
		namesFlux_transform().subscribe(e -> System.out.println(e));

	}

	public static Mono<String> nameMono_flatMapMany() {
		return Mono.just("shiva");
	}

	public static Flux<String> namesFlux() {
		return Flux.fromIterable(List.of("sai", "narasimha", "durga", "narayana"));
//		return Flux.fromIterable(List.of("sai", "narasimha", "durga", "narayana")).log();
	}

	public static Mono<String> nameMono() {
		return Mono.just("sandrananda");
//		return Mono.just("sandrananda").log();
	}

	public static Mono<List<String>> splitNameMono(String s) {
		return Mono.just(List.of(s.split("")));
	}

	public static Mono<List<String>> nameMono_Flatmap() {
		return Mono.just("kalyan").map(String::toUpperCase).flatMap(FluxAndMonoService2::splitNameMono);
	}

	public static Flux<String> spliName(String s) {
		return Flux.fromArray(s.split(""));
	}

	public static Flux<String> namesFlux_transform() {
		Function<Flux<String>, Flux<String>> filterMap = name -> name.filter(e -> e.length() > 3)
				.map(String::toUpperCase);
		return Flux.fromIterable(List.of("sai", "narasimha", "durga", "narayana")).transform(filterMap)
				.flatMap(FluxAndMonoService2::spliName);
	}

}
