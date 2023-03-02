package com.optum.flux;

import java.util.concurrent.atomic.AtomicReference;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.optum.util.Util;

import reactor.core.publisher.Flux;

public class SubscriptionExample {
	public static void main(String[] args) {
		AtomicReference<Subscription> reference = new AtomicReference<>();
		Flux.range(1, 20).log().subscribeWith(new Subscriber<Integer>() {

			@Override
			public void onSubscribe(Subscription s) {
				System.out.println("Received subscription " + s);
				reference.set(s);
			}

			@Override
			public void onNext(Integer t) {
				System.out.println("onNext " + t);
			}

			@Override
			public void onError(Throwable t) {
				System.err.println("onError " + t.getMessage());
			}

			@Override
			public void onComplete() {
				System.out.println("onComplete");
			}
		});

		Util.sleepSeconds(5);
		reference.get().request(3);
		Util.sleepSeconds(3);
		reference.get().request(3);
		Util.sleepSeconds(3);
		System.out.println("going to cancel subscription");
		reference.get().cancel();
		Util.sleepSeconds(5);
		reference.get().request(4);
		Util.sleepSeconds(3);
	}
}
