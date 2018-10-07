package matador;

import java.util.Scanner;

import wrapperClasses.Player;

public class MatadorMain {
	private static Scanner sc = new Scanner(System.in);
	private static boolean playing = true;
	private static Player[] players;
	
	public static void init(int numPlayers) {
		players = new Player[numPlayers];
		for (int i = 0; i < players.length; i++) {
			System.out.println("Indtast navn på spiller " + (i + 1) + ": ");
			players[i] = new Player(sc.nextLine());
		}
	}
	
	public static void startGameLoop() {
		while (playing) {
			
		}
	}
	
	public static void stop() {
		sc.close();
	}
	
	public static void endGame() {
		playing = false;
	}
}
