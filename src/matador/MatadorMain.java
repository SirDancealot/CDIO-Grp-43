package matador;

import java.util.Scanner;

import util.CustomStreamTokenizer;
import wrapperClasses.Player;


public class MatadorMain {
	private CustomStreamTokenizer cst = new CustomStreamTokenizer();
	private static boolean playing = true;
	private static Player[] players;
	
	public static void init(int numPlayers, int AIs) {
		CustomStreamTokenizer.initTokenizer();
		
		players = new Player[numPlayers + AIs];
		for (int i = 0; i < numPlayers; i++) {
			System.out.print("Indtast navn på spiller " + (i + 1) + ": ");
			players[i] = new Player();
		}
		for (int i = 0; i < AIs; i++) {
			players[numPlayers + i] = new Player();
		}
	}
	
	public static void startGameLoop() {
		int turns = 1;
		int numPlayer = 0;
		while (playing) {
			players[numPlayer].promptPlayer("test");
			if (++numPlayer >= players.length) {
				turns ++;
				numPlayer = 0;
			}
		}
	}
	
	public static void stop() {
	}
	
	public static void endGame() {
		playing = false;
	}
}
