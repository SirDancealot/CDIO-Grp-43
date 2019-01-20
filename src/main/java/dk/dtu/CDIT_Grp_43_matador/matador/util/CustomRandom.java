package dk.dtu.CDIT_Grp_43_matador.matador.util;

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

}
