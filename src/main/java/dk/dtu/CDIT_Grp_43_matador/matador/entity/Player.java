package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import java.io.IOException;
import java.util.ArrayList;

import dk.dtu.CDIT_Grp_43_matador.matador.language.*;
import dk.dtu.CDIT_Grp_43_matador.matador.util.CustomStreamTokenizer;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.*;

public class Player {
	private String name;
	private boolean isAI = false;
	private static int aiNum = 1;
	private boolean inJail = false;
	private static GameBoard bord = GameBoard.getInstance();
	private boolean hasWon = false;
	private static Lang lang;
	private Tile currTile;
	private int roll;
	private int currPos = 0;
	private boolean firstTurn = true;
	private ArrayList<Tile> ownedTiles = new ArrayList<Tile>();
	private ArrayList<ChanceCard> keepingCards = new ArrayList<ChanceCard>();

	// Konto
    private Account playerAccount = new Account(20);

	/**
	 * @param name the name this player has.
	 */
    public Player(String name) {
		this.name = name;
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
	
	public boolean moveTo(String tileName) {
		Tile targetTile = bord.getTileByName(tileName);
		int targetPos = targetTile.getTileIndex() - currPos;
		if (targetPos < 0)
			targetPos += bord.getBoardSize();
		return move(targetPos);
	}

	public void setAI(boolean isAI) {
		this.isAI = isAI;
	}

	@Override
	public String toString() {
		return name;
	}


	/**
	 * Calculates whether {@code This} instance of the {@code Player} has won and set's their hasWon tag respectively.
	 */

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
	
	public void setMoney(int money) {
		playerAccount.setMoney(money);
	}

// Getters and setters

	public boolean isInJail() {
		return inJail;
	}
	public boolean isAI() {
        return isAI;
    }
    public boolean hasWon() {
        return hasWon;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return playerAccount.getMoney();
    }
    public int getRoll() {
        return roll;
    }
    public int getCurrPos() {
        return currPos;
    }
    
    public boolean isFirstTurn() {
		return firstTurn;
	}

    public static void setLang(Lang lang) {
        Player.lang = lang;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }
    
    public void setFirstTurn(boolean firstTurn) {
		this.firstTurn = firstTurn;
	}
    
    public void addOwnedTile(Tile t) {
    	ownedTiles.add(t);
	}
    
    public ArrayList<Tile> getOwnedTiles() {
		return ownedTiles;
	}
    
    public void addKeepingCard(ChanceCard card) {
    	keepingCards.add(card);
    }
    
    public ArrayList<ChanceCard> getKeepingCards() {
		return keepingCards;
	}
}
