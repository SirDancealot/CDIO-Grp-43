package dk.dtu.CDIT_Grp_43_matador.matador;


import java.io.IOException;

import dk.dtu.CDIT_Grp_43_matador.matador.gui.GUI_Controller;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tile;
import dk.dtu.CDIT_Grp_43_matador.matador.util.*;

//import static dk.dtu.CDIT_Grp_43_matador.matador.util.GameTextures.createGameBoardTextures;

public class GameController {
	//Singleton instance and getter
	private static final GameController INSTANCE = new GameController();

	private GameController() {}
	
	public static GameController getInstance() {
		return INSTANCE;
	}
	
	//Logical variables
	private static int turns = 1;
	private static boolean playing = true;
	private String turnInfo;

	//Container variables
	private Player[] players;
	private GameBoard bord = GameBoard.getInstance();
	private ChanceCardDeck deck = ChanceCardDeck.getInstance();
	private Logic logic = Logic.getINSTANCE();
	private GUI_Controller gui = GUI_Controller.getINSTANCE();
	private InformationExchanger infExch = InformationExchanger.getInstance();


	public void init() throws IOException {


		// Gui
		gui.init();
		gui.setupGame();
		int numPlayers = gui.getNumberOfPlayers();
		int startMoney = 1500;
		String[] names = gui.getNames();
		gui.addplayers(startMoney);
		gui.displayPlayers();

		bord.initBoard();
		players = new Player[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			players[i] = new Player(names[i], startMoney);
		}

		// Init logic
		logic.init(players);
	}
	
	/**
	 * The main game loops, that indefinitely runs through each player until one of the players (or AI's) has won
	 * @throws IOException if an I/O error occurs.
	 */
	public void startGameLoop() throws IOException {
		while (playing) {
			logic.tick();
			if(logic.isEndOfGame()){
				endGame();
				System.out.println("Game end");
				displayWinningMessage();
			}
			gui.updateDisplay();
		}
	}

	
	/**
	 * The stop function that runs as the very last thing in the game 
	 * in case any objects needs to be closed or anything similar.
	 */
	public void stop() {
	}

	/**
	 * The function that is run when a player has won the game and the game loop needs to stop.
	 */
	private void endGame() {
		playing = false;
	}

	private void displayWinningMessage() {
		infExch.setEndOfGame(true);
		//infExch.setCurrentTurnText("");
		infExch.addToCurrentTurnText("\n" + infExch.getCurrPlayer() + " ran out of money and the game has now ended\n");
        Player winner = infExch.getCurrPlayer();
        for (Player player : players) {
			if (player.getScore() > winner.getScore())
				winner = player;
			else if (player.getScore() == winner.getScore()) {
				int playerScore = player.getScore();
				int winnerScore = winner.getScore();
				
				for (Tile tile : player.getOwnedTiles()) {
					playerScore += tile.getTileValue();
				}
				for (Tile tile : winner.getOwnedTiles()) {
					playerScore += tile.getTileValue();
				}
				
				if (playerScore > winnerScore)
					winner = player;
			}
		}
        infExch.addToCurrentTurnText("The winner of the game was " + winner + " with a score of " + winner.getScore());
        //gui.updateDisplay();
	}

	public String getChoice(String msg, String[] Buttons) {
		return "lul";
	}

	public void resetGame(){
		playing = true;
		turns = 1;
	}

	public String displayButtons(String msg, String... buttons) {
		return gui.displayButtons(msg, buttons);
	}

	public void updateDisplay() {
		gui.updateDisplay();
	}

	public void displayMessage(String msg) {
	    gui.displayMessage(msg);
    }

	public boolean isPlaying() {
		return playing;
	}

	public int getTurn(){
		return turns;
	}

	public Player[] getPlayers() {
		return players;
	}

	public GameBoard getBord() {
		return bord;
	}

    public void setTurnInfo(String turnInfo) {
        this.turnInfo = turnInfo;
    }

    public String getTurnInfo() {
        return turnInfo;
    }

    public int getUserInt(String msg) {
		return gui.getUserInt(msg);
    }
}
