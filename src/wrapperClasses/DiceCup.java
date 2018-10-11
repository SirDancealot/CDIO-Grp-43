package wrapperClasses;

import entity.Die;

public class DiceCup {
	private static final DiceCup INSTANCE = new DiceCup();
	
	private static Die d1 = new Die(6);
	private static Die d2 = new Die(6);
	
	private DiceCup() { }
	
	public static DiceCup getInstance() {
		return INSTANCE;
	}
	/**
	 * Rolls the two dice in the DiceCup
	 * @return Returns the sum of the two rolls
	 */
	public static int roll() {
		return d1.roll() + d2.roll();
	}
	
	/**
	 * @return Returns the sum of the current rolls on the two dice without re-rolling them
	 */
	public static int getDiceIntValues() {
		return d1.getFaceValue() + d2.getFaceValue();
	}
	
	/**
	 * @return Returns whether the value shown on the two dice are the same
	 */
	public static boolean isSame() {
		return d1.getFaceValue() == d2.getFaceValue();
	}
	
	/**
	 * @param num
	 * @return Returns whether the value on the two dice and the same and have the specified {@code num} shown on them
	 */
	public static boolean isSameAndNum(int num) {
		return isSame() && d1.getFaceValue() == num;
	}
	
	/**
	 * @return Returns the current value of the first {@code Die}
	 */
	public static int getD1Val() {
		return d1.getFaceValue();
	}

	/**
	 * @return Returns the current value of the second {@code Die}
	 */
	public static int getD2Val() {
		return d2.getFaceValue();
	}
	
	/**
	 * @return Returns a string representing the value of the two dice
	 */
	public static String getDiceStringValues() {
		return "a " + d1.getFaceValue() + " and a " + d2.getFaceValue();
	}
}
