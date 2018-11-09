package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import java.io.IOException;

import dk.dtu.CDIT_Grp_43_matador.matador.Matador;
import dk.dtu.CDIT_Grp_43_matador.matador.language.*;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.*;
import dk.dtu.CDIT_Grp_43_matador.matador.util.*;

public class Player {
	public void setName(String name) {
		this.name = name;
	}

	private String name;
	
	private boolean isAI = false;
	private static int aiNum = 1;
	
	private static GameBoard bord = GameBoard.getInstance();
	private boolean lastTwoSixes = false;
	private boolean hasWon = false;
	private static final int winScore = 3000;
	private static Lang lang;
	private Tile currTile;
	private int roll;
	
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
	
	public boolean isAI() {
		return isAI;
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

	public int getScore() {
		return score;
	}

	public int getRoll() {
		return roll;
	}

	/**
	 * The function that should be called every time an action is required of a {@code Player}, 
	 * also works if the {@code Player} is an AI.
	 * @param {@code msg}
	 * @throws IOException 
	 */
	public void playerRollDice() throws IOException {
		System.out.println(" ");
		roll = DiceCup.roll(); //rolls the dice and saves the value
		currTile = bord.landOnTile(roll);

        // Adding tile value to account
		score += currTile.getTileValue();

		

		if (isAI) { //If player is an AI rolls automatically
			System.out.println(name + lang.getTag("Player:playerRolling")); //tag: playerRolling
			//Matador.getGame().getTextArea().append(name + lang.getTag("Player:playerRolling"));
			//Matador.getGame().getTextArea().append("\n");
		} else { //If player is an actual player it waits for an input from the player
			System.out.print(lang.getTag("Player:turnRoll")+" "+name+"," + lang.getTag("Player:enterRoll")); //tag: turnRoll //tag: enterRoll
			CustomStreamTokenizer.waitForInput();
			
			//Matador.getGame().getTextArea().append(lang.getTag("Player:turnRoll")+" "+name+"," + lang.getTag("Player:enterRoll"));
			//Matador.getGame().getTextArea().append("\n");
			//CustomStreamTokenizer.waitForInput();
		}

		System.out.println(name + lang.getTag("Player:arrivedAt") + currTile.getTileName());
		System.out.println(currTile.getTileMessage());
		System.out.println(name + lang.getTag("Player:playerTotalScore") + score);
		System.out.println();
		
		/*
		//Prints information to the player
		System.out.println(name + lang.getTag("Player:playerRolled") +" "+ roll +lang.getTag("Player:landedOn")+" "+currTile.getTileName()+lang.getTag("Player:rolledResult")+" "+ currTile.getTileValue()); //tag: playerRolled //tag: rolledResult //tag: landedOn
		System.out.println(name + lang.getTag("Player:playerTotalScore") +" "+ score); //tag: playerTotalScore
		Matador.getGame().getTextArea().append(name + lang.getTag("Player:playerRolled") +" "+ roll +lang.getTag("Player:landedOn")+" "+currTile.getTileName()+lang.getTag("Player:rolledResult")+" "+ currTile.getTileValue());
		Matador.getGame().getTextArea().append("\n");
		Matador.getGame().getTextArea().append(name + lang.getTag("Player:playerTotalScore") +" "+ score);
		Matador.getGame().getTextArea().append("\n");
		Matador.getGame().getTextArea().append("\n");
		calcHasWon(); //calculates whether the player has won now

		*/
		calcHasWon();
		if (currTile.givesExtraTurn() && !hasWon) { //gives player an extra turn if they haven't won, and they rolled two identical
			//System.out.println(name+" " + lang.getTag("Player:additionalRoll")); //tag: additionalRoll
			//Matador.getGame().getTextArea().append(name+" " + lang.getTag("Player:additionalRoll"));
			//Matador.getGame().getTextArea().append("\n");
			playerRollDice();
		}
		
	}
}
