package dk.dtu.CDIT_Grp_43_matador.matador;


import java.io.IOException;

import dk.dtu.CDIT_Grp_43_matador.matador.GUI.MatodorGUI;
import dk.dtu.CDIT_Grp_43_matador.matador.language.*;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.*;
import dk.dtu.CDIT_Grp_43_matador.matador.util.*;

import static dk.dtu.CDIT_Grp_43_matador.matador.util.GameTextures.createGameBoardTextures;

public class Matador {

	private static int turns = 1;
	private CustomStreamTokenizer cst = new CustomStreamTokenizer();
	private static boolean playing = true;

	public static Player[] getPlayers() {
		return players;
	}

	private static Player[] players;
	private static final String[] LANGS = LanguageController.getLangs();
	private static GameBoard bord = GameBoard.getInstance();

	public static Lang getLang() {
		return lang;
	}

	private static Lang lang;
	private static Timer timer;
	private static int currPlayer = 0;
	private static DiceCup diceCup = DiceCup.getInstance();
	private static MatodorGUI game;
	public static GameBoard getBord() {
		return bord;
	}

	public static MatodorGUI getGame() {
		return game;
	}

	/**
	 * The init function initializes everything that needs to be, all the players, ais, lang and more.
	 * @param args
	 * @throws IOException
	 */
	public static void init(String[] args) throws IOException {
		int numPlayers = 0;
		int AIs = 0;
		int langIndex = 0;
		int tps = 20;

		//The Custom Stream Tokenizer is initialized
		CustomStreamTokenizer.initTokenizer();

		// GUI
		//createGameBoardTextures();
		//game = new MatodorGUI(800, 800);
		
		//Does different things dependent on the length of the args array
		switch (args.length) {
		case 4:
			tps = Integer.valueOf(args[3]);
		case 3: //if the length is 3 or higher a language for the game is specified and then initialized here
			for (int i = 0; i < LANGS.length; i++) {
				if (LANGS[i].equals(args[2])) {
					langIndex = i;
					break;
				}
			}
		case 2: //if the length is 2 or higer the number of ai's are specified and the number set here
			try {
				AIs = Integer.valueOf(args[1]);
			} catch (Exception e) {
				
			}
		case 1://if the lenght is 1 or higer the number of players are specified and the number is set here
			try {
				numPlayers = Integer.valueOf(args[0]);
			} catch (Exception e) {
				
			}
		default: //if the length is 0 or higer this runs as the last and initializes the players, per default numPlayers, AIs and langIndex is set to default values, and then changed if the length of args was higer then 0
			timer = new Timer(tps);
			timer.initTimer();
			initLang(langIndex);
			//diceCup.changeCustomDice(new int[] {5}, new int[] {5});
			bord.initBoard();
			players = new Player[numPlayers + AIs];
			for (int i = 0; i < numPlayers; i++) {
				System.out.print(lang.getTag("Matador:enterPlayerName") + (i + 1) + ": "); //tag: enterPlayerName
				players[i] = new Player(CustomStreamTokenizer.nextString());
			}
			for (int i = 0; i < AIs; i++) {
				players[numPlayers + i] = new Player();
			}
			break;
		}
	}
	
	/**
	 * The main game loops, that indefinitely runs through each player until one of the players (or AI's) has won
	 * @throws IOException
	 */
	public static void startGameLoop() throws IOException {
		while (playing) {
			if (timer.getMissingTicks() > 0) {
				timer.tick();
				tick();
			}
			update();
		}
	}
	
	public static void tick() throws IOException {
		players[currPlayer].playerRollDice();
		if (players[currPlayer].hasWon()) {
			System.out.println(players[currPlayer].toString()+" "+ lang.getTag("Matador:wonIn") +" " + turns +" "+ lang.getTag("Matador:turns"));//tag: wonInTurns //tag: turns
			endGame();
		}
		else if (++currPlayer >= players.length) {
			turns ++;
			currPlayer = 0;
		}
	}
	
	private static void update() {
		timer.update();
	}
	
	/**
	 * The stop function that runs as the very last thing in the game 
	 * in case any objects needs to be closed or anything similar.
	 */
	public static void stop() {
	}
	
	private static void initLang(int langIndex) throws IOException {
		LanguageController.initLang(langIndex);
		lang = LanguageController.getCurrentLanguage();
		Player.setLang(lang);
		bord.setLang(lang);
	}
	
	/**
	 * The function that is run when a player has won the game and the game loop needs to stop.
	 */
	private static void endGame() {
		playing = false;
	}

	public static int getCurrPlayer() {
		return currPlayer;
	}

	public static void resetGame(){
		playing = true;
		turns = 1;
	}

	public static boolean isPlaying() {
		return playing;
	}
	public static int getTurn(){
		return turns;
	}
}
