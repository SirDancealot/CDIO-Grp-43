package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import java.io.IOException;

import dk.dtu.CDIT_Grp_43_matador.matador.Matador;
import dk.dtu.CDIT_Grp_43_matador.matador.language.*;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.*;


public class Player {
	private String name;
	
	private boolean isAI = false;
	private static int aiNum = 1;
	
	private boolean lastTwoSixes = false;
	private boolean hasWon = false;
	private static final int winScore = 3000;

	// Konto

    private Konto playerKonto = new Konto(1000);
	private int score = playerKonto.getInitialAmount();
	
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
	
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Calculates whether {@code This} instance of the {@code Player} has won and set's their hasWon tag respectively.
	 */
	private void calcHasWon() {
		if (score >= winScore) {
				hasWon = true;
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

        // Adding tile value to account

		String tilesLandendOn = Matador.getMatadorGameBourd().getGameTiles()[roll-1].getTileName();

        int added = Matador.getMatadorGameBourd().getGameTiles()[roll-1].getTileValue();

		score += added;


		if (isAI) { //If player is an AI rolls automatically
			System.out.println(name + LanguageController.getHmap().get("playerRolling")); //tag: playerRolling
		} else { //If player is an actual player it waits for an input from the player
			System.out.print("It's your turn to roll " + name + " press enter to roll"); //tag: turnRoll //tag: enterRoll
			CustomStreamTokenizer.waitForInput();
		}

		//Prints information to the player
		System.out.println(name + " rolled " + Integer.toString(roll) +" landed on "+tilesLandendOn+" for a total of " + Integer.toString(added)); //tag: playerRolled //tag: rolledResult
		System.out.println(name + " now has a total score of " + score); //tag: playerTotalScore
		calcHasWon(); //calculates whether the player has won now

		
		if ( roll == 10) { //gives player an extra turn if they haven't won, and they rolled two identical
			System.out.println(name + " rolled 10 and get's another roll"); //tag: additionalRoll
			playerRollDice();
		}
	}
}
