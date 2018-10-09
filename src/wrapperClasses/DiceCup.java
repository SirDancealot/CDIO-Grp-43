package wrapperClasses;

public class DiceCup {
	private static final DiceCup INSTANCE = new DiceCup();
	
	private static Die d1 = new Die(6);
	private static Die d2 = new Die(6);
	
	private DiceCup() { }
	
	public static DiceCup getInstance() {
		return INSTANCE;
	}
	
	public static int roll() {
		return d1.roll() + d2.roll();
	}
	
	public static int getDiceIntValues() {
		return d1.getFaceValue() + d2.getFaceValue();
	}
	
	public static boolean isSame() {
		return d1.getFaceValue() == d2.getFaceValue();
	}
	
	public static boolean isSameAndNum(int num) {
		return isSame() && d1.getFaceValue() == num;
	}
	
	public static int getD1Val() {
		return d1.getFaceValue();
	}

	public static int getD2Val() {
		return d2.getFaceValue();
	}
	
	public static String getDiceStringValues() {
		return "a " + d1.getFaceValue() + " and a " + d2.getFaceValue();
	}
}
