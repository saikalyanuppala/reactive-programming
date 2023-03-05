package com.optum.threads;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class ThreadDemo {
	public static void main(String[] args) {

		Flux<Object> flux = Flux.create(fluxSink -> {
			printThreadName("create");
			fluxSink.next(1);
			fluxSink.complete();
		}).doOnNext(m -> printThreadName("doOnNext " + m));

		Runnable runnable = () -> flux.subscribe(v -> printThreadName("subscribe " + v));
		for (int i = 0; i < 2; i++)
			new Thread(runnable).start();

		Util.sleepSeconds(5);
	}

	public static void printThreadName(String msg) {
		System.out.println(msg + "\t: Thread name " + Thread.currentThread().getName());
	}
}
