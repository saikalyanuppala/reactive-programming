package com.optum.threads;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOnDemo {
	public static void main(String[] args) {

		Flux<Object> flux = Flux.create(fluxSink -> {
			printThreadName("create");
			fluxSink.next(1);
			fluxSink.complete();
		}).doOnNext(m -> printThreadName("doOnNext " + m));

		Runnable ruunable = () -> flux.doFirst(() -> printThreadName("doFirst2"))
				.subscribeOn(Schedulers.boundedElastic()).doFirst(() -> printThreadName("doFirst1"))
				.subscribe(v -> printThreadName("subscribe " + v));

		for (int i = 0; i < 2; i++)
			new Thread(ruunable).start();

		Util.sleepSeconds(5);
	}

	public static void printThreadName(String msg) {
		System.out.println(msg + "\t: Thread name " + Thread.currentThread().getName());
	}
}
