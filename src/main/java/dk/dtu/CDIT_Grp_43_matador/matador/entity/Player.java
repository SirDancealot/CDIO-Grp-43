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
	private int currPos = 0;

	// Konto
    private Account playerAccount = new Account(1000);
	
	/**
	 * @param name the name this player has.
	 */
    public Player(String name) {
		this.name = name;
	}

	/**
	 * Constructor for initializing {@code Player} as an AI
	 */
	public Player() {
		this.name = "AI " + aiNum;
		aiNum++;
		this.isAI = true;
	}

	/**
	 * Moves the player the assisgned number around the board and handles wraparound the board
	 * @param moving How long the player has to move
	 * @return Returns true if players moved all the way around the board else returns false
	 */

	public boolean move(int moving){
		currPos += moving;

		if(currPos >= bord.getBoardSize()){
			currPos-=bord.getBoardSize();
			return true;
		}

		return false;
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
	public static void setLang(Lang lang) {
		Player.lang = lang;
	}

	public int getCurrPos() {
		return currPos;
	}

	public boolean addMoney (int money) {
		return playerAccount.addMoney(money);
	}
	public boolean withDrawMoney(int money) {
		return playerAccount.withdrawMoney(money);
	}
	public boolean payMoney (Player p, int money) {
		if (this.withDrawMoney(money)) {
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
