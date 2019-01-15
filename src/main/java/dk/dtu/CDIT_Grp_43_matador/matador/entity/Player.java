package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import java.util.ArrayList;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Property;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tile;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;

public class Player {
	private static Player[] players;
	private String name;
	private boolean inJail = false;
	private static GameBoard bord;
	private int roll;
	private int currPos = 0;
	private int oldPos = 0;
	private int cardMove = 0;
	private boolean startMoneyElegible = false;
	private ArrayList<Tile> ownedTiles = new ArrayList<Tile>();
	private ArrayList<ChanceCard> keepingCards = new ArrayList<ChanceCard>();
    private Account playerAccount;
	private boolean nextJailFree = false;
	private boolean inAuction = false;
	private boolean payDouble = false;

	/**
	 * @param name the name this player has.
	 * @param startMoney how much money this player starts with.
	 */
    public Player(String name, int startMoney) {
		bord = GameBoard.getInstance();
		this.name = name;
		playerAccount = new Account(startMoney);
	}


	/**
	 * Moves the player the assisgned number around the board and handles wraparound the board
	 * @param moving How long the player has to move
	 * @return Returns true if players moved all the way around the board else returns false
	 */

	public boolean move(int moving){
		roll = moving;
		oldPos = currPos;
		currPos += moving;
		currPos = (currPos + bord.getBoardSize()) % bord.getBoardSize();
		return true;
	}

	public boolean moveByCard(int moving){
		cardMove = moving;
		currPos += moving;
		currPos = (currPos + bord.getBoardSize()) % bord.getBoardSize();
		return true;
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

	// We need to add value of houses and hotels.
	public int playerFortune() {
		int ppf = 0;
		for (Tile tile : ownedTiles)
			ppf += tile.getTileValue();
		return ppf + getScore();
	}

	/**
	 * A method to get how many houses and hotels the player owns
	 * @return a integer array of length 2 with index 0 being how many houses they own, and index 1 being hou many hotels they own
	 */
	public int[] getHouseAndHotelsOwned() {
		int[] houseAndHotels = new int[2];

		for (Tile tile : ownedTiles) {
			if (tile instanceof Property) {
				int houseLevel = ((Property)tile).getHouseLevel();
				if (houseLevel == 5)
					houseAndHotels[1]++;
				else
					houseAndHotels[0] += houseLevel;
			}
		}
		return houseAndHotels;
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
    
    public boolean isStartMoneyElegible() {
		return startMoneyElegible;
	}

    public void setName(String name) {
        this.name = name;
    }
    
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }
    
    public void setStartMoneyElegible(boolean startMoneyElegible) {
		this.startMoneyElegible = startMoneyElegible;
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

	public boolean isInAuction() {
		return inAuction;
	}

	public void setInAuction(boolean inAuction) {
		this.inAuction = inAuction;
	}

	public void setPayDouble(boolean payDouble) {
		this.payDouble = payDouble;
	}

	public boolean isPayDouble() {
		return payDouble;
	}

	public Tile[] getTilesByTag(String tag) {
		return bord.searchForTileType(tag);
	}

	public int getOldPos() {
		return oldPos;
	}

	public int getCardMove() {
		return cardMove;
	}

	public void setCardMove(int cardMove) {
		this.cardMove = cardMove;
	}
}
