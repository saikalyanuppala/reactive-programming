package com.optum.operators;

import java.util.function.Function;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class SwitchOnFirst {
	public static void main(String[] args) {

		getPersons().switchOnFirst((signal, personFlux) -> {
			System.out.println("Inside swtich on first");
			return signal.isOnNext() && signal.get().getAge() > 30 ? personFlux : applyFilters().apply(personFlux);
		}).subscribe(Util.subscriber());
	}

	private static Flux<Person> getPersons() {
		return Flux.range(1, 10).map(i -> new Person());
	}

	private static Function<Flux<Person>, Flux<Person>> applyFilters() {
		return flux -> flux.filter(p -> p.getAge() > 30).doOnNext(p -> p.setName(p.getName().toUpperCase()))
				.doOnDiscard(Person.class, p -> System.out.println("discarded person " + p));
	}
}
