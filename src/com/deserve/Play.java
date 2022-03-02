package com.deserve;

import java.util.Random;

public class Play {

	int serve() {
		int result;
		Random randomNumberGenerator = new Random();
		result = randomNumberGenerator.nextInt(2);
		return Math.abs(result) % 2;
	}
}