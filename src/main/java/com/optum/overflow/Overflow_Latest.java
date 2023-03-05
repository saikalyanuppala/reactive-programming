package com.optum.overflow;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Overflow_Latest {
	public static void main(String[] args) {
	       System.setProperty("reactor.bufferSize.small", "16");
			Flux.create(fluxSink -> {
				for (int i = 1; i <= 500; i++) {
					fluxSink.next(i);
					System.out.println("Pushed " + i);
					Util.sleepMillis(1);
				}
				fluxSink.complete();
			})
			.onBackpressureLatest() //added this call
			.publishOn(Schedulers.boundedElastic())
			.doOnNext(i -> Util.sleepMillis(10))
			.subscribe(Util.subscriber());
			
			Util.sleepSeconds(10);
		}
}
