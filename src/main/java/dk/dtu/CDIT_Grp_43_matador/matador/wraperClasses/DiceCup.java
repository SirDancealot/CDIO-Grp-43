package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.*;
import dk.dtu.CDIT_Grp_43_matador.matador.language.Lang;

public class DiceCup {
	private static final DiceCup INSTANCE = new DiceCup();
	
	private static Die d1 = new Die(6);
	private static Die d2 = new Die(6);
	private static Lang lang;
	
	private DiceCup() { }

    /**
     * Changes the dice to a standard die of given size
     * @param d1newSize is the new number of sides for d1
     * @param d2newSize is the new number of sides for d2
     */
	public void changeRegularDice(int d1newSize, int d2newSize){
	    d1 = new Die(d1newSize);
	    d2 = new Die(d2newSize);
    }
    /**
     * Changes the dice to have specific sides with specific values
     * @param d1sides an integer array that describes the number of sides for d1 to have
     * @param d2sides an integer array that describes the number of sides for d2 to have
     */
    public void changeCustomDice(int[] d1sides, int[] d2sides ){
	    d1 = new Die(d1sides);
	    d2 = new Die(d2sides);
    }
	
    /**
     * @return Returns the singleton instance og DiceCup
     */
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
		return lang.getTag("DiceCup:d1") + d1.getFaceValue() + lang.getTag("DiceCup:d1") + d2.getFaceValue();
	}
	
	public static void setLang(Lang lang) {
		DiceCup.lang = lang;
	}
}
