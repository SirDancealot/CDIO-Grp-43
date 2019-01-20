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
	private static boolean playing = true;

	//Container variables
	private Player[] players;
	private GameBoard bord = GameBoard.getInstance();
	private Logic logic = Logic.getINSTANCE();
	private GUI_Controller gui = GUI_Controller.getINSTANCE();


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
	 */
	public void startGameLoop() {
		while (playing) {
			logic.tick();
			if(logic.isEndOfGame()){
				endGame();
			}
		}
	}

	/**
	 * The stop function that runs as the very last thing in the game 
	 * in case any objects needs to be closed or anything similar.
	 */
	public void stop() {
		System.out.println("Game end");
		displayWinningMessage();
		System.exit(0);
	}

	/**
	 * The function that is run when a player has won the game and the game loop needs to stop.
	 */
	private void endGame() {
		playing = false;
	}

	private void displayWinningMessage() {
        Player winner = logic.getWinner();
        for (Player player : players) {
			if (player.getScore() > winner.getScore())
				winner = player;
		}
		String winMsg = "The winner of the game was " + winner + " with a score of " + winner.getScore() + " and a total fortune of " + winner.playerFortune() + "\n\nThe game will close after you press ok";
        displayMessage(winMsg);
	}

	public String getChoice(String msg, Boolean list, String... buttons) {
		String choice = gui.displayButtons(msg, list, buttons);
		return choice;
	}


	public void updateDisplay(String turnInfo) {
		gui.updateDisplay(turnInfo);
	}


	public GameBoard getBord() {
		return bord;
	}

    public int getUserInt(String msg,int min, int max) {
		return gui.getUserInt(msg ,min, max);
    }
}
