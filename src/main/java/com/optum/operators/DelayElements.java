package com.optum.operators;

import java.time.Duration;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class DelayElements {
	public static void main(String[] args) {
		
		System.setProperty("reactor.bufferSize.x", "9");
		Flux.range(1, 50).log().delayElements(Duration.ofSeconds(1)).subscribe(Util.subscriber());
		
		Util.sleepSeconds(60);
	}
}
