package com.optum.operators;

import java.util.function.Function;

import com.optum.util.Util;

import lombok.Data;
import lombok.ToString;
import reactor.core.publisher.Flux;

public class Transform {
	public static void main(String[] args) {
		getPersons()
		.transform(applyFilters())
		.subscribe(Util.subscriber());
	}

	private static Flux<Person> getPersons() {
		return Flux.range(1, 10).map(i -> new Person());
	}

	private static Function<Flux<Person>, Flux<Person>> applyFilters() {
		return flux -> flux
				.filter(p -> p.getAge() > 40)
				.doOnNext(p -> p.setName(p.getName().toUpperCase()))
				.doOnDiscard(Person.class, p -> System.out.println("discarded person "+p));
	}
}

@Data
@ToString
class Person {
	private Integer age;
	private String name;

	public Person() {
		this.name = Util.faker().name().fullName();
		this.age = Util.faker().random().nextInt(20, 60);
	}
}