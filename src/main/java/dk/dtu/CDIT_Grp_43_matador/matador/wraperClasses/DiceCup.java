package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.*;

public class DiceCup {
	private static final DiceCup INSTANCE = new DiceCup();
	
	private static Die d1 = new Die(6);
	private static Die d2 = new Die(6);
	private static int sameInARow = 0;
	
	private DiceCup() { }

    /**
     * Changes the dice to a standard die of given size
     * @param d1newSize is the new number of sides for d1
     * @param d2newSize is the new number of sides for d2
     */
	public void changeRegularDice(int d1newSize, int d2newSize){
	    d1 = new Die(d1newSize);
		//d2 = new Die(d2newSize);
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

    public boolean ThreeSame(){
		if(sameInARow == 3) {
			sameInARow = 0;
			return true;
		}
		return false;
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
	public int roll() {
		int diceSum = d1.roll() + d2.roll();
		if (isSame())
			sameInARow++;
		else
			sameInARow = 0;
		return 3;//d1.roll() + d2.roll();
	}
	
	/**
	 * @return Returns the sum of the current rolls on the two dice without re-rolling them
	 */
	public int getDiceIntValues() {
		return 3;//d1.getFaceValue() + d2.getFaceValue();
	}
	
	/**
	 * @return Returns whether the value shown on the two dice are the same
	 */

	public boolean isSame() {
		return d1.getFaceValue() == d2.getFaceValue();
	}

	/**
	 * @param num the number to test whether both die equals
	 * @return Returns whether the value on the two dice and the same and have the specified {@code num} shown on them
	 */

	public boolean isSameAndNum(int num) {
		return isSame() && d1.getFaceValue() == num;
	}

	/**
	 * @return Returns the current value of the first {@code Die}
	 */
	public int getD1Val() {
		return d1.getFaceValue();
	}

	/**
	 * @return Returns the current value of the second {@code Die}
	 */

	public int getD2Val() {
		return d2.getFaceValue();
	}
	/**
	 * @return Returns a string representing the value of the two dice
	 */

	public String getDiceStringValues() {
		return "Die 1 shows a: " + d1.getFaceValue() + " and die 2 shows a: " + d2.getFaceValue();
	}
}
