package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import java.io.IOException;

import dk.dtu.CDIT_Grp_43_matador.matador.Matador;
import dk.dtu.CDIT_Grp_43_matador.matador.language.*;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.*;
import dk.dtu.CDIT_Grp_43_matador.matador.util.*;

public class Player {
	private String name;
	
	private boolean isAI = false;
	private static int aiNum = 1;
	
	private static GameBoard bord = GameBoard.getInstance();
	private boolean lastTwoSixes = false;
	private boolean hasWon = false;
	private static final int winScore = 3000;
	private static Lang lang;
	
	// Konto

    private Konto playerKonto = new Konto(1000);
	private int score = playerKonto.getInitialAmount();
	
	/**
	 * @param {@code Player} name
	 * @return A {@code Player} instantiated as an actual player
	 */

	public Player(String name) {
		this.name = name;
	}

	public static void setLang(Lang lang) {
		Player.lang = lang;
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
	 * @param {@code msg}
	 * @throws IOException 
	 */
	public void playerRollDice() throws IOException {
		System.out.println(" ");
		int roll = DiceCup.roll(); //rolls the dice and saves the value

        // Adding tile value to account

		String tilesLandendOn = bord.getGameTiles()[roll-1].getTileName();

        int added = Matador.getMatadorGameBourd().getGameTiles()[roll-1].getTileValue();

		score += added;


		if (isAI) { //If player is an AI rolls automatically
			System.out.println(name + lang.getTag("Player:playerRolling")); //tag: playerRolling
		} else { //If player is an actual player it waits for an input from the player
			System.out.print(lang.getTag("Player:turnRoll")+" "+name+"," + lang.getTag("Player:enterRoll")); //tag: turnRoll //tag: enterRoll
			CustomStreamTokenizer.waitForInput();
		}

		//Prints information to the player
		System.out.println(name + lang.getTag("Player:playerRolled") +" "+ Integer.toString(roll) +lang.getTag("Player:landedOn")+" "+tilesLandendOn+lang.getTag("Player:rolledResult")+" "+ Integer.toString(added)); //tag: playerRolled //tag: rolledResult //tag: landedOn
		System.out.println(name + lang.getTag("Player:playerTotalScore") +" "+ score); //tag: playerTotalScore
		calcHasWon(); //calculates whether the player has won now

		
		if (DiceCup.isSame() && roll == 10) { //gives player an extra turn if they haven't won, and they rolled two identical
			System.out.println(name+" " + lang.getTag("Player:additionalRoll")); //tag: additionalRoll
			playerRollDice();
		}
	}
}
