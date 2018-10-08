package matador;

import java.io.IOException;

import util.CustomStreamTokenizer;
import wrapperClasses.Player;


public class MatadorMain {
	private CustomStreamTokenizer cst = new CustomStreamTokenizer();
	private static boolean playing = true;
	private static Player[] players;
	
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
	
	public static void startGameLoop() throws IOException {
		int turns = 1;
		int currPlayer = 0;
		while (playing) {
			players[currPlayer].promptPlayer();
			if (players[currPlayer].hasWon())
				endGame();
			if (++currPlayer >= players.length) {
				turns ++;
				currPlayer = 0;
			}
		}
		
	}
	
	public static void stop() {
	}
	
	private static void endGame() {
		playing = false;
	}
}
