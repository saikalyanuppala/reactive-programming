package com.optum.util;

import java.util.function.Consumer;

import org.reactivestreams.Subscriber;

import com.github.javafaker.Faker;

public class Util {

	private static final Faker FAKER = Faker.instance();

	public static Faker faker() {
		return FAKER;
	}

	public static Consumer<Object> onNext() {
		return o -> System.out.println("onNext -> " + o);
	}

	public static Consumer<Throwable> onError() {
		return e -> System.err.println("Error -> " + e.getMessage());
	}

	public static Runnable onComplete() {
		return () -> System.out.println("onComplete() completed");
	}

	public static void sleepSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static Subscriber<Object> subscriber() {
		return new DefaultSubscriber();
	}

	public static Subscriber<Object> subscriber(String name) {
		return new DefaultSubscriber(name);
	}

}
