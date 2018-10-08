package wrapperClasses;

public class Player {
	private static int aiNum = 1;
	private static int score = 0;
	private String name;
	private boolean isAI = false;
	private boolean hasWon = false;
	private Die d1, d2;
	
	/**
	 * @param name
	 * @return constructor for a player instantiated as an actual player
	 */
	public Player(String name) {
		this.name = name;
		d1 = new Die(6);
		d2 = new Die(6);
	}

	/**
	 * @return the constructor for a player but instantiated as an AI
	 */
	public Player() {
		this.name = "AI " + aiNum;
		aiNum++;
		this.isAI = true;
	}
	
	public void setAI(boolean isAI) {
		this.isAI = isAI;
	}
	
	public String getName() {
		return name;
	}

	public boolean hasWon() {
		return hasWon;
	}
	
	private void calcHasWon() {
		if (score >= 40)
			hasWon = true;
	}

	/**
	 * The function that should be called every time an action is required of a {@code Player}, also works if the {@code Player} is an AI.
	 * @param msg
	 */
	public void promptPlayer(String msg) {
		int roll = roll();
		score += roll;
		calcHasWon();
		if (isAI) {
			System.out.println(name + " is rolling it's dice");
			System.out.println(name + " rolled a " + d1.getFaceValue() + " and a " + d2.getFaceValue() + " for a total of: " + roll);
		} else {
			System.out.println("It's your turn to roll " + name);
		}
	}
	
	private int roll() {
		return d1.roll() + d2.roll();
	}
}
