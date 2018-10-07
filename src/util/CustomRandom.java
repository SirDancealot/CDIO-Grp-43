package util;

import java.util.Random;

public class CustomRandom {
	private static Random rand = new Random(System.currentTimeMillis());
	
	public static int randInt(int maxNum) {
		return rand.nextInt(maxNum + 1);
	}
	
	public static int randIntRange(int low, int high) {
		return rand.nextInt(high + 1 - low) + low;
	}
}
