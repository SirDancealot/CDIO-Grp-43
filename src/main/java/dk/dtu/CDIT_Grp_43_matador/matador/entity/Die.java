package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import dk.dtu.CDIT_Grp_43_matador.matador.util.*;

import java.util.Random;


public class Die {
	private int size;
	private int faceValue;
	private int sides[];
	private static Random rand = new Random(System.currentTimeMillis());

	/**
	 * Initializes a {@code Die} with a set size and rolls a value for the die
	 * @param size the number of sides this die will have.
	 */
	public Die(int size) {
		this.size = size;
		roll();
	}

	public Die(int sides[]) {
		this.sides = sides;
		roll();
	}

	/**
	 * Rolls a new random value for the die based on the {@code Random} class
	 *
	 * @return A random number which the die can roll
	 */
	public int roll() {
		if (sides == null) {
			faceValue = rand.nextInt(size)+1;
			return faceValue;
		} else {
			faceValue = sides[rand.nextInt(6)];
			return faceValue;
		}
	}

		public int getFaceValue () {
			return faceValue;
		}

		@Override
		public String toString () {
			return "This die has " + size + " sides, and currently shows a " + faceValue;
		}

}
