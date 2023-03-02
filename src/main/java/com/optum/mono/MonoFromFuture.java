package com.optum.mono;

import java.util.concurrent.CompletableFuture;

import com.optum.util.Util;

import reactor.core.publisher.Mono;

public class MonoFromFuture {

	public static CompletableFuture<String> getName() {
		return CompletableFuture.supplyAsync(() -> Util.faker().name().fullName());
	}

	public static void main(String[] args) {
		Mono.fromFuture(getName()).subscribe(Util.onNext());
		Util.sleepSeconds(2);
	}
}
