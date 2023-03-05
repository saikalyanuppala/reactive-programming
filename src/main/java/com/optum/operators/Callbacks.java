package com.optum.operators;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class Callbacks {
	public static void main(String[] args) {

		//observe doFirst and doSubscribe call backs.
		Flux.create(fluxSink -> {
			System.out.println("inside create");
			for (int i = 0; i < 5; i++) {
				fluxSink.next(i);
			}
			fluxSink.complete();
			System.out.println("-- completed");
		})
		        .doFirst(() -> System.out.println("doFirst 1"))  
		        .doOnCancel(() -> System.out.println("doOnCancel"))
		        .doOnComplete(() -> System.out.println("doOnComplete"))
				.doOnError(err -> System.err.println("doOnError " + err.getMessage()))
				.doOnNext(obj -> System.out.println("doOnNext " + obj))
				.doFirst(() -> System.out.println("doFirst 2"))  
				.doOnSubscribe(s -> System.out.println("doOnSubscribe 1 " + s))
				.doOnTerminate(() -> System.out.println("doOnTerminate"))
				.doFinally(f -> System.out.println("doFinally " + f))
				.doOnRequest(req -> System.out.println("doOnRequest " + req))
				.doFirst(() -> System.out.println("doFirst 3"))  
				.doOnSubscribe(s -> System.out.println("doOnSubscribe 2 " + s))
				.doOnDiscard(Object.class, obj -> System.out.println("doOnDiscard "+obj))
				.subscribe(Util.subscriber());
		
				System.out.println("---- check for error -------------");
		// 		observe doError and call back.
				Flux.create(fluxSink -> {
					System.out.println("inside create");
					for (int i = 0; i < 5; i++) {
						fluxSink.next(i);
					}
					fluxSink.error(new RuntimeException("oops error"));
					System.out.println("-- completed");
				})
				        .doFirst(() -> System.out.println("doFirst 1"))  
				        .doOnCancel(() -> System.out.println("doOnCancel"))
				        .doOnComplete(() -> System.out.println("doOnComplete"))
						.doOnError(err -> System.err.println("doOnError " + err.getMessage()))
						.doOnNext(obj -> System.out.println("doOnNext " + obj))
						.doFirst(() -> System.out.println("doFirst 2"))  
						.doOnSubscribe(s -> System.out.println("doOnSubscribe 1 " + s))
						.doOnTerminate(() -> System.out.println("doOnTerminate"))
						.doFinally(f -> System.out.println("doFinally " + f))
						.doOnRequest(req -> System.out.println("doOnRequest " + req))
						.doFirst(() -> System.out.println("doFirst 3"))  
						.doOnSubscribe(s -> System.out.println("doOnSubscribe 2 " + s))
						.doOnDiscard(Object.class, obj -> System.out.println("doOnDiscard "+obj))
						.subscribe(Util.subscriber());
				
				System.out.println("---- check for take,  -------------");
				// 		observe doOnDiscard , doOnCanel and doFinally call backs.
						Flux.create(fluxSink -> {
							System.out.println("inside create");
							for (int i = 0; i < 5; i++) {
								fluxSink.next(i);
							}
                                fluxSink.complete();
							System.out.println("-- completed");
						})
						        .doFirst(() -> System.out.println("doFirst 1"))  
						        .doOnCancel(() -> System.out.println("doOnCancel"))
						        .doOnComplete(() -> System.out.println("doOnComplete"))
								.doOnError(err -> System.err.println("doOnError " + err.getMessage()))
								.doOnNext(obj -> System.out.println("doOnNext " + obj))
								.doFirst(() -> System.out.println("doFirst 2"))  
								.doOnSubscribe(s -> System.out.println("doOnSubscribe 1 " + s))
								.doOnTerminate(() -> System.out.println("doOnTerminate"))
								.doFinally(f -> System.out.println("doFinally 1 " + f))
								.doOnRequest(req -> System.out.println("doOnRequest " + req))
								.doFirst(() -> System.out.println("doFirst 3"))  
								.doOnSubscribe(s -> System.out.println("doOnSubscribe 2 " + s))
								.doOnDiscard(Object.class, obj -> System.out.println("doOnDiscard "+obj))
								.take(2)
								.doFinally(f -> System.out.println("doFinally 2 " + f))
								.subscribe(Util.subscriber());

	}
}
