package wrapperClasses;

import java.util.Random;

public class CustomRandom {
	private static Random rand = new Random(System.currentTimeMillis());
	
	/**
	 * @param maxNum
	 * @return returns a random whole number between 1 and maxNum, both inclusive
	 */
	public static int randInt(int maxNum) {
		return rand.nextInt(maxNum) + 1;
	}
	
	/**
	 * @param low
	 * @param high
	 * @return returns a random whole number between low and high, both inclusive
	 */
	public static int randIntRange(int low, int high) {
		return rand.nextInt(high + 1 - low) + low;
	}
	
	public static Random getRandIntance() {
		return rand;
	}
}
