package com.optum.util;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DefaultSubscriber implements Subscriber<Object> {

	private String name = "";

	public DefaultSubscriber() {
		super();
	}

	public DefaultSubscriber(String name) {
		super();
		this.name = name + " - ";
	}

	@Override
	public void onSubscribe(Subscription s) {
		s.request(Long.MAX_VALUE);

	}

	@Override
	public void onNext(Object t) {
		System.out.println(threadName() + name + " onNext " + t);
	}

	@Override
	public void onError(Throwable t) {
		System.err.println(threadName() + name + " ERROR " + t.getMessage());
	}

	@Override
	public void onComplete() {
		System.out.println(threadName() + name + " onComplete()");
	}

	public String threadName() {
		return Thread.currentThread().getName() + " ";
	}
}
