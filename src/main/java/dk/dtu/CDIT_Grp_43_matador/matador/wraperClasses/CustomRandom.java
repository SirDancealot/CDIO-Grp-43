package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import java.util.Random;

public class CustomRandom {
	private static Random rand = new Random(System.currentTimeMillis());
	
	/**
	 * @param maxNum the maximum number the random will return, maxNum included.
	 * @return returns a random whole number between 1 and maxNum, both inclusive.
	 */
	public static int randInt(int maxNum) {
		return rand.nextInt(maxNum) + 1;
	}
	
	/**
	 * @param low the lowest roll the method will be able to roll.
	 * @param high the maximum roll the method will be able to roll.
	 * @return returns a random whole number between low and high, both inclusive
	 */
	public static int randIntRange(int low, int high) {
		return rand.nextInt(high + 1 - low) + low;
	}
	
	public static Random getRandIntance() {
		return rand;
	}
}
