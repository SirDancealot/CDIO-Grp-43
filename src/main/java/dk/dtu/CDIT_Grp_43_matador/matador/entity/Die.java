package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import dk.dtu.CDIT_Grp_43_matador.matador.language.*;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.*;
import dk.dtu.CDIT_Grp_43_matador.matador.util.*;


public class Die {
	private int size;
	private int faceValue;
	private int sides[];
	private static Lang lang;

	/**
	 * Initializes a {@code Die} with a set size and rolls a value for the die
	 *
	 * @param size
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
	 * Rolls a new random value for the die based on the our {@code CustomRandom} class
	 *
	 * @return Returns a random number which the die can roll
	 */
	public int roll() {
		if (sides == null) {
			faceValue = CustomRandom.randInt(size);
			return faceValue;
		} else {
			faceValue = sides[CustomRandom.randInt(sides.length - 1)];
			return faceValue;
		}
	}

		public int getSize () {
			return size;
		}

		public int getFaceValue () {
			return faceValue;
		}

		public void setFaceValue ( int faceValue){
			this.faceValue = faceValue;
		}

		@Override
		public String toString () {
			return lang.getTag("Die:sizeOfSides") +" "+ size + lang.getTag("Die:faceValueOfDie") + faceValue;
		}
}