package com.optum.mono;

import com.optum.util.Util;

import reactor.core.publisher.Mono;

public class MonoFromRunnable {

	public static void main(String[] args) {
		Mono.fromRunnable(timeConsuming()).subscribe(Util.onNext(), Util.onError(),
				() -> System.out.println("Sending mails"));
	}

	private static Runnable timeConsuming() {
		return () -> {
			Util.sleepSeconds(3);
			System.out.println("Operation completed..");
		};
	}
}
