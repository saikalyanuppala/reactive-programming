package com.optum.repeatretry;

import java.util.concurrent.atomic.AtomicInteger;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class Repeat {

	private static AtomicInteger atomicInteger = new AtomicInteger(1);

	public static void main(String[] args) {
		getNumbers().repeat(2).subscribe(Util.subscriber());

		System.out.println("-------- another example ----------");
		getNumbers1().repeat(2).subscribe(Util.subscriber());

		System.out.println("-------- another example2 ----------");
		getNumbers1().repeat(() -> atomicInteger.get() < 20).subscribe(Util.subscriber());
	}

	private static Flux<Integer> getNumbers() {
		return Flux.range(1, 3).doOnSubscribe(s -> System.out.println("Subscribed"))
				.doOnComplete(() -> System.out.println("doOnComplete"));
	}

	private static Flux<Integer> getNumbers1() {
		return Flux.range(1, 3).doOnSubscribe(s -> System.out.println("Subscribed"))
				.doOnComplete(() -> System.out.println("doOnComplete")).map(i -> atomicInteger.getAndIncrement());
	}
}

//repeat -- Resubscribe after complete signal
//retry -- Resubscribe after error
