package com.optum.overflow;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Overflow_Buffer_Size {
	public static void main(String[] args) {
			System.setProperty("reactor.bufferSize.small", "16");
			Flux.create(fluxSink -> {
				for (int i = 1; i <= 200; i++) {
					fluxSink.next(i);
					System.out.println("Pushed " + i);
				}
				fluxSink.complete();
			})
			.onBackpressureBuffer(20, o -> System.out.println("Dropped "+o)) //default value.
			.publishOn(Schedulers.boundedElastic())
			.doOnNext(i -> Util.sleepMillis(10))
			.subscribe(Util.subscriber());
			
			Util.sleepSeconds(30);
		}
}
