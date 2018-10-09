package wrapperClasses;

import util.CustomRandom;

public class Die {
	private int size;
	private int faceValue;
	
	/**
	 * Initializes a {@code Die} with a set size and rolls a value for the die
	 * @param size
	 */
	public Die(int size) {
		this.size = size;
		roll();
	}
	
	/**
	 * Rolls a new random value for the die based on the our {@code CustomRandom} class
	 * @return Returns a random number which the die can roll
	 */
	public int roll() {
		faceValue = CustomRandom.randInt(size);
		return faceValue;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getFaceValue() {
		return faceValue;
	}
	
	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}
	
	@Override
	public String toString() {
		return "This dice has " + size + " sides and currently has rolled a " + faceValue;
	}
}
