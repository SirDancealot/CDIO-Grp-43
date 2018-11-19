package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import java.io.IOException;
import dk.dtu.CDIT_Grp_43_matador.matador.language.*;
import dk.dtu.CDIT_Grp_43_matador.matador.util.CustomStreamTokenizer;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.*;

public class Player {
	public void setName(String name) {
		this.name = name;
	}

	private String name;
	
	private boolean isAI = false;
	private static int aiNum = 1;
	private boolean inJail = false;
	
	private static GameBoard bord = GameBoard.getInstance();
	private boolean hasWon = false;
	private static final int winScore = 3000;
	private static Lang lang;
	private Tile currTile;
	private int roll;
	
	// Konto
    private Account playerAccount = new Account(1000);
	
	/**
	 * @param name the name this player has.
	 */
    public Player(String name) {
		this.name = name;
	}
	public static void setLang(Lang lang) {
		Player.lang = lang;
	}


	/**
	 * Constructor for initializing {@code Player} as an AI
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
		if (playerAccount.getMoney() >= winScore) {
				hasWon = true;
		}
	}
	public int getScore() {
		return playerAccount.getMoney();
	}
	public int getRoll() {
		return roll;
	}


	/**
	 * The function that should be called every time an action is required of a {@code Player}, 
	 * also works if the {@code Player} is an AI.
	 * @throws IOException if an I/O error occurs.
	 */
	public void playerRollDice() throws IOException {
		System.out.println(" ");
		roll = DiceCup.roll(); //rolls the dice and saves the value
		currTile = bord.landOnTile(roll);

        // Adding tile value to account
		playerAccount.addMoney(currTile.getTileValue());

		

		if (isAI) { //If player is an AI rolls automatically
			System.out.println(name + lang.getTag("Player:playerRolling")); //tag: playerRolling
		} else { //If player is an actual player it waits for an input from the player
			System.out.print(lang.getTag("Player:turnRoll") + name + "," + lang.getTag("Player:enterRoll")); //tag: turnRoll //tag: enterRoll
			CustomStreamTokenizer.waitForInput();
		}

		System.out.println(name + lang.getTag("Player:arrivedAt") + currTile.getTileName() + lang.getTag("Player:rolledResult") + roll);
		System.out.println(currTile.getTileMessage());
		System.out.println(name + lang.getTag("Player:playerTotalScore") + playerAccount.getMoney());
		System.out.println();
		calcHasWon();

		if (currTile.givesExtraTurn() && !hasWon) { //gives player an extra turn if they haven't won, and they rolled two identical
			playerRollDice();
		}
		
	}

	public boolean addMoney (int money) {
		return playerAccount.addMoney(money);
	}
	public boolean withdrawMoney (int money) {
		return playerAccount.withdrawMoney(money);
	}
	public boolean payMoney (Player p, int money) {
		if (this.withdrawMoney(money)) {
			if (p.addMoney(money)) {
				return true;
			}
		} return false;
	}

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	public boolean isInJail() {
		return inJail;
	}
}
