package dk.dtu.CDIT_Grp_43_matador.matador;


import java.io.IOException;

import dk.dtu.CDIT_Grp_43_matador.matador.gui.GUI_Controller;
import dk.dtu.CDIT_Grp_43_matador.matador.language.*;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.*;
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
	
	//Container variables
	private static Player[] players;
	private static final String[] LANGS = LanguageController.getLangs();
	private static GameBoard bord = GameBoard.getInstance();
	private static ChanceCardDeck deck = ChanceCardDeck.getInstance();
	private static Lang lang;
	private static LogicController logic = LogicController.getINSTANCE();
	private static GUI_Controller gui_controller = GUI_Controller.getINSTANCE();


	public void init() throws IOException {

		//The Custom Stream Tokenizer is initialized
		CustomStreamTokenizer.initTokenizer();

		// Gui
		gui_controller.setupGame(LANGS);
		gui_controller.addplayers(gui_controller.getNames(), 20);
		gui_controller.displayPlayers(gui_controller.getAllPlayer());
		int numPlayers = gui_controller.getNumberOfPlayers();
		int langIndex = gui_controller.getLangIndex();
		String[] names = gui_controller.getNames();

		initLang(langIndex);
		bord.initBoard();
		deck.init();

		players = new Player[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			players[i] = new Player(names[i]);
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

			if(logic.isEndOfGame()){
				playing = false;
				System.out.println("Game end");

			}
			update();
		}
	}

	private void update() {
		logic.tick();
		gui_controller.updateDisplay();
	}
	
	/**
	 * The stop function that runs as the very last thing in the game 
	 * in case any objects needs to be closed or anything similar.
	 */
	public void stop() {
	}
	
	private void initLang(int langIndex) throws IOException {
		LanguageController.initLang(langIndex);
		lang = LanguageController.getCurrentLanguage();
		Player.setLang(lang);
		DiceCup.setLang(lang);
	}
	
	/**
	 * The function that is run when a player has won the game and the game loop needs to stop.
	 */
	private void endGame() {
		playing = false;
	}

	public void resetGame(){
		playing = true;
		turns = 1;
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

	public Lang getLang() {
		return lang;
	}

	public GameBoard getBord() {
		return bord;
	}


}
