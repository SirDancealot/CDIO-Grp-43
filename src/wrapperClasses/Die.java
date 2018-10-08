package wrapperClasses;

import util.CustomRandom;

public class Die {
	private int size;
	private int faceValue;
	
	public Die(int size) {
		this.size = size;
		roll();
	}
	
	public int roll() {
		faceValue = CustomRandom.randInt(size);
		return faceValue;
	}
	
	public int getFaceValue() {
		return faceValue;
	}
	
	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}
}