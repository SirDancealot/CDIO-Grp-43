package matador.entity;

import java.io.IOException;

import matador.wrapperClasses.CustomStreamTokenizer;
import matador.wrapperClasses.DiceCup;

public class Player {
	private String name;
	
	private boolean isAI = false;
	private static int aiNum = 1;
	
	private boolean lastTwoSixes = false;
	private boolean hasWon = false;
	private boolean lastOverWinscore = false;
	private static final int winScore = 40;
	private int score = 0;
	
	/**
	 * @param Player name
	 * @return A {@code Player} instantiated as an actual player
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * @return A {@code Player} instantiated as an AI
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
	
	/**
	 * Calculates whether {@code This} instance of the {@code Player} has won and set's their hasWon tag respectively.
	 */
	private void calcHasWon() {
		if (score >= 40) {
			if (!lastOverWinscore) {
				lastOverWinscore = true;
			}
			else if (DiceCup.isSame()) {
				System.out.println(name + " has won the game");
				hasWon = true;
			}
			if (!hasWon)
				System.out.println(name + " now has a score of over 40 and only need to roll two of the same dice to win");
		}
	}

	/**
	 * The function that should be called every time an action is required of a {@code Player}, 
	 * also works if the {@code Player} is an AI.
	 * @param msg
	 * @throws IOException 
	 */
	public void playerRollDice() throws IOException {
		System.out.println(" ");
		int roll = DiceCup.roll(); //rolls the dice and saves the value
		score += roll;
		if (DiceCup.isSameAndNum(1)) //If both dice shows 1 score gets reset
			score = 0;
		if (isAI) { //If player is an AI rolls automatically
			System.out.println(name + " is rolling their dice");
		} else { //If player is an actual player it waits for an input from the player
			System.out.print("It's your turn to roll " + name + " press enter to roll");
			CustomStreamTokenizer.waitForInput();
		}
		//Prints information to the player
		System.out.println(name + " rolled " + DiceCup.getDiceStringValues() + " for a total of " + DiceCup.getDiceIntValues());
		System.out.println(name + " now has a total score of " + score);
		if (DiceCup.isSameAndNum(1)) { //Prints information to player if their score got resat
			System.out.println(name + " rolled two one's and gets their score reset to 0");
			lastOverWinscore = false;
		}
		calcHasWon(); //calculates whether the player has won now
		if (DiceCup.isSameAndNum(6)) { 
			//If both dice rolled a 6  it checks for if they rolled two sixes last time and if they did they won
			if (lastTwoSixes) {
				System.out.println(name + " has rolled two sixes twice in a row, and hereby wins the game");
				hasWon = true;
			}
			else {
				System.out.println(name + " has rolled two sixes in one roll, if they do it again they win the game");
				lastTwoSixes = true;
			}
		} else
			lastTwoSixes = false;
		
		if (DiceCup.isSame() && !hasWon) { //gives player an extra turn if they haven't won, and they rolled two identical 
			System.out.println(name + " rolled two identical dice and get's another roll");
			playerRollDice();
		}
	}
}
