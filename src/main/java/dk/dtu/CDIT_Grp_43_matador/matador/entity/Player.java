package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import java.util.ArrayList;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tile;
import dk.dtu.CDIT_Grp_43_matador.matador.util.InformationExchanger;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;

public class Player {
	private static InformationExchanger infExch = InformationExchanger.getInstance();
	private static Player[] players;
	private String name;
	private boolean inJail = false;
	private static GameBoard bord = GameBoard.getInstance();
	private int roll;
	private int currPos = 0;
	private boolean firstTurn = true;
	private ArrayList<Tile> ownedTiles = new ArrayList<Tile>();
	private ArrayList<ChanceCard> keepingCards = new ArrayList<ChanceCard>();
    private Account playerAccount;
	private boolean nextJailFree = false;

	/**
	 * @param name the name this player has.
	 * @param startMoney how much money this player starts with.
	 */
    public Player(String name, int startMoney) {
		this.name = name;
		playerAccount = new Account(startMoney);
	}


	/**
	 * Moves the player the assisgned number around the board and handles wraparound the board
	 * @param moveing How long the player has to move
	 * @return Returns true if players moved all the way around the board else returns false
	 */

	public boolean move(int moveing){
		currPos += moveing;

		if(currPos >= bord.getBoardSize()){
			currPos-=bord.getBoardSize();
		}
		infExch.addToCurrentTurnText(this + " rolled a " + moveing + " landed on " + bord.getGameTiles()[currPos].toString() + "\n");
		return bord.landOnTile(this);
	}
	
	public boolean moveTo(String tileName) {
		Tile targetTile = bord.getTileByName(tileName);
		int targetPos = targetTile.getTileIndex() - currPos;
		if (targetPos < 0)
			targetPos += bord.getBoardSize();
		return move(targetPos);
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
	public boolean payMoney(Player p, int money) {
		if (this == p)
			return true;
		if (this.withDrawMoney(money)) {
			if (p.addMoney(money)) {
				return true;
			}
		} return false;
	}
	
	public boolean payAll(int money) {
		for (Player player : players) {
			if(!payMoney(player, money))
				return false;
		}
		return true;
	}
	
	public void setMoney(int money) {
		playerAccount.setMoney(money);
	}

// Getters and setters

	public boolean isInJail() {
		return inJail;
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
    
    public boolean hasFreeJail() {
    	return nextJailFree;
	}
    
    public void setFreeJail(boolean freeJail) {
    	this.nextJailFree = freeJail;
    }
    
    public static void setPlayers(Player[] players) {
		Player.players = players;
	}
}
