package dk.dtu.CDIT_Grp_43_matador.matador;

import java.io.IOException;

public class Main {
	/**
	 * The function that runs when the program is executed, it registrers wheter any arguments are given, 
	 * then initializes the game with given arguments if there are any, otherwise it uses the default values, 
	 * then the main game loop is started, and when that exits it closes the program
	 * @param args the settitings registrerd when the program is launched.
	 * @throws IOException if an I/O error occurs.
	 */
	public static void main(String[] args) throws IOException {
		final GameController game = GameController.getInstance();
		game.init();
		System.out.println("hey");
		game.testingGetChoice();
		//game.startGameLoop();
		game.stop();
	}
}
