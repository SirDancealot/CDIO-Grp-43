package matador;

import java.io.IOException;

public class MatadorMain {
	/**
	 * The function that runs when the program is executed, it registrers wheter any arguments are given, 
	 * then initializes the game with given arguments if there are any, otherwise it uses the default values, 
	 * then the main game loop is started, and when that exits it closes the program
	 * @param args
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		boolean defaultMode = args.length < 1;
		int defaultPlayers = 2;
		boolean defaultAIMode = args.length < 2;
		int defaultAIPlayers = 0;
		try {
			Matador.init(defaultMode ? defaultPlayers : Integer.valueOf(args[0]), defaultAIMode ? defaultAIPlayers : Integer.valueOf(args[1]));
		} catch (Exception e) {
			Matador.init(defaultPlayers, defaultAIPlayers);
		}
		Matador.startGameLoop();
		Matador.stop();
	}
}
