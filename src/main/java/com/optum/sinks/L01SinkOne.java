package com.optum.sinks;

import com.optum.util.Util;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.One;

public class L01SinkOne {
	public static void main(String[] args) {

		One<Object> oneSink = Sinks.one();
		
		Mono<Object> asMono = oneSink.asMono();
		asMono.subscribe(Util.subscriber("sai"));
		
		oneSink.tryEmitValue("Hello sinks");
	}
}
