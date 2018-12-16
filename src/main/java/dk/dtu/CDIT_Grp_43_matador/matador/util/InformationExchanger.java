package dk.dtu.CDIT_Grp_43_matador.matador.util;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;

/**
 * A Class to convey information between {@code LogicController} and {@code GUI_Controller}
 * @author mj
 *
 */
public class InformationExchanger {
	private static final InformationExchanger INSTANCE = new InformationExchanger();
	private Player[] players;
	private Player currPlayer;
	private int currPlayerScore;
	private int currPlayerOldPos = 0;
	private int currPlayerNewPos = 0;
	private int currPlayerIndex;
	private int currPlayerRolled;
	private int cardMove = 0;
	private boolean tileOwned = false;
	private String currentTurnText;
	private static boolean endOfGame = false;
	
	
	private InformationExchanger() { }

	public void addToCurrentTurnText(String currentTurnText) {
		this.currentTurnText += currentTurnText;
	}

	public String getCurrentTurnText() {
		return currentTurnText;
	}
	public void setCurrentTurnText(String currentTurnText) {
		this.currentTurnText = currentTurnText;
	}

	public Player getCurrPlayer() {
		return currPlayer;
	}
	
	public void setCurrPlayer(Player currPlayer) {
		this.currPlayer = currPlayer;
	}
	
	public int getCurrPlayerIndex() {
		return currPlayerIndex;
	}
	
	public void setCurrPlayerIndex(int currPlayerIndex) {
		this.currPlayerIndex = currPlayerIndex;
	}
	
	public void setEndOfGame(boolean endOfGame) {
		InformationExchanger.endOfGame = endOfGame;
	}
	
	public int getCurrPlayerNewPos() {
		return currPlayerNewPos;
	}
	
	public void setCurrPlayerNewPos(int currPlayerNewPos) {
		this.currPlayerNewPos = currPlayerNewPos;
	}

	public void setTileOwned(boolean tileOwned) {
		this.tileOwned = tileOwned;
	}
	
	public void setCardMove(int cardMove) {
		this.cardMove = cardMove;
	}

	public boolean isTileOwned() {
		return tileOwned;
	}

	public int getCurrPlayerOldPos() {
		return currPlayerOldPos;
	}
	
	public void setCurrPlayerOldPos(int currPlayerOldPos) {
		this.currPlayerOldPos = currPlayerOldPos;
	}
	
	public int getCurrPlayerRolled() {
		return currPlayerRolled;
	}
	
	public void setCurrPlayerRolled(int currPlayerRolled) {
		this.currPlayerRolled = currPlayerRolled;
	}
	
	public int getCurrPlayerScore() {
		return currPlayerScore;
	}
	
	public void setCurrPlayerScore(int currPlayerScore) {
		this.currPlayerScore = currPlayerScore;
	}
	
	public static InformationExchanger getInstance() {
		return INSTANCE;
	}
	
	public int getCardMove() {
		return cardMove;
	}
	
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public boolean isEndOfGame() {
		return endOfGame;
	}
}
