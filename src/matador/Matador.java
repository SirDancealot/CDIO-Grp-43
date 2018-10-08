package matador;

import java.io.IOException;

public class Matador {
	public static void main(String[] args) throws NumberFormatException, IOException {
		boolean defaultMode = args.length == 0;
		int defaultPlayers = 2;
		boolean defaultAIMode = !(args.length == 2);
		int defaultAIPlayers = 2;
		
		MatadorMain.init(defaultMode ? defaultPlayers : Integer.valueOf(args[0]), defaultAIMode ? defaultAIPlayers : Integer.valueOf(args[1]));
		MatadorMain.startGameLoop();
		MatadorMain.stop();
	}
}
