package com.optum.util;

import com.github.javafaker.Faker;

public class FakerDemo {
	public static void main(String[] args) {
		Faker faker = Faker.instance();
		for (int i = 0; i < 10; i++) {
			System.out.println(faker.name().fullName() + "--- " + faker.address().fullAddress());
		}
	}
}
