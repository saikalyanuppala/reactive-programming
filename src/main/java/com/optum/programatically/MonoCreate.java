package com.optum.programatically;

import com.optum.util.Util;

import reactor.core.publisher.Mono;

public class MonoCreate {
	public static void main(String[] args) {
		Mono.create(monosink -> {
			monosink.success("single value");
		}).subscribe(Util.subscriber());
	}
}
