package dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.*;

public class DiceCup {
	private static final DiceCup INSTANCE = new DiceCup();
	
	private static Die d1 = new Die(6);
	private static Die d2 = new Die(6);
	private static int sameInARow = 0;
	
	private DiceCup() { }

    public boolean threeSame(){
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
		if (isSame())
			sameInARow++;
		else
			sameInARow = 0;
		return d1.roll() + d2.roll();
	}
	
	/**
	 * @return Returns the sum of the current rolls on the two dice without re-rolling them
	 */
	public int getDiceIntValues() {
		return d1.getFaceValue() + d2.getFaceValue();
	}
	
	/**
	 * @return Returns whether the value shown on the two dice are the same
	 */

	public boolean isSame() {
		return d1.getFaceValue() == d2.getFaceValue();
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

}
