package wrapperClasses;

import java.io.IOException;

import util.CustomStreamTokenizer;

public class Player {
	private static int aiNum = 1;
	private int score = 0;
	private String name;
	private boolean isAI = false;
	private boolean hasWon = false;
	private Die d1, d2;
	private boolean lastTwoSixes = false;
	
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
		d1 = new Die(6);
		d2 = new Die(6);
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
		if (score >= 40) {
			if (isSame()) {
				System.out.println(name + " has won the game");
				hasWon = true;
			}
			else {
				System.out.println(name + " now has a score of over 40 and only need to roll two of the same dice to win");
			}
		}
	}

	/**
	 * The function that should be called every time an action is required of a {@code Player}, also works if the {@code Player} is an AI.
	 * @param msg
	 * @throws IOException 
	 */
	public void promptPlayer() throws IOException {
		System.out.println(" ");
		int roll = roll();
		score += roll;
		if (isAI) {
			System.out.println(name + " is rolling it's dice");
			System.out.println(name + " rolled a " + d1.getFaceValue() + " and a " + d2.getFaceValue() + " for a total of: " + roll);
		} else {
			System.out.print("It's your turn to roll " + name + " press enter to roll");
			CustomStreamTokenizer.waitForInput();
			System.out.println("You rolled a " + d1.getFaceValue() + " and a " + d2.getFaceValue() + " for a total of " + roll);
		}
		if (isSame() && d1.getFaceValue() == 1) {
			System.out.println(name + " rolled two 1's and gets their score reset to 0");
			score = 0;
		}
		System.out.println(name + " now has a total of " + score + " points");
		calcHasWon();
		if (isSame() && d1.getFaceValue() == 6) {
			if (lastTwoSixes) {
				System.out.println(name + " has rolled two 6's twice in a row and herpy wins");
				hasWon = true;
			}
			lastTwoSixes = true;
		} else {
			lastTwoSixes = false;
		}
		if (isSame() && !hasWon) {
			System.out.println(name + " rolled two of the same dice and gets an extra turn");
			promptPlayer();
		}
	}
	
	private int roll() {
		return d1.roll() + d2.roll();
	}
	
	private boolean isSame() {
		return d1.getFaceValue() == d2.getFaceValue();
	}
}
