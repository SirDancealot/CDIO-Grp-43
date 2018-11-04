package dk.dtu.CDIT_Grp_43_matador.matador;

import java.io.IOException;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.CustomStreamTokenizer;


public class Matador {
	private CustomStreamTokenizer cst = new CustomStreamTokenizer();
	private static boolean playing = true;
	private static Player[] players;
	
	/**
	 * The init function initializes the game with given number of players, AI's
	 * and initializes any static classes necessary
	 * @param numPlayers
	 * @param AIs
	 * @throws IOException
	 */
	public static void init(int numPlayers, int AIs) throws IOException {
		CustomStreamTokenizer.initTokenizer();
		
		players = new Player[numPlayers + AIs];
		for (int i = 0; i < numPlayers; i++) {
			System.out.print("Indtast navn på spiller " + (i + 1) + ": ");
			players[i] = new Player(CustomStreamTokenizer.nextString());
		}
		for (int i = 0; i < AIs; i++) {
			players[numPlayers + i] = new Player();
		}
	}
	
	/**
	 * The main game loops, that indefinitely runs through each player until one of the players (or AI's) has won
	 * @throws IOException
	 */
	public static void startGameLoop() throws IOException {
		int turns = 1;
		int currPlayer = 0;
		while (playing) {
			players[currPlayer].playerRollDice();
			if (players[currPlayer].hasWon())
				endGame();
			if (++currPlayer >= players.length) {
				turns ++;
				currPlayer = 0;
			}
		}
		
	}
	
	/**
	 * The stop function that runs as the very last thing in the game 
	 * in case any objects needs to be closed or anything similar.
	 */
	public static void stop() {
	}
	
	/**
	 * The function that is run when a player has won the game and the game loop needs to stop.
	 */
	private static void endGame() {
		playing = false;
	}
}
