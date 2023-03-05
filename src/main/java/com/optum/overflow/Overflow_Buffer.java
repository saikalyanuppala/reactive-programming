package com.optum.overflow;

import com.optum.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Overflow_Buffer {
	public static void main(String[] args) {
       //overflow strategies are buffer, drop, latest, error
		Flux.create(fluxSink -> {
			for (int i = 1; i <= 500; i++) {
				fluxSink.next(i);
				System.out.println("Pushed " + i);
			}
			fluxSink.complete();
		})
		.onBackpressureBuffer() //default value.
		.publishOn(Schedulers.boundedElastic())
		.doOnNext(i -> Util.sleepMillis(10))
		.subscribe(Util.subscriber());
		
		Util.sleepSeconds(30);
	}
}


//buffer -> keep in memory
//drop -> once the queue is full , new items will be dropped
//latest -> once the queue is full, keep 1 latest item as and when it arrives. drop old
//error -> throw error to the downstream 