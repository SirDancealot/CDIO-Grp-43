package matador;

public class Matador {
	public static void main(String[] args) {
		boolean debugMode = args.length == 0;
		int debugPlayers = 2;
		int debugAIPlayers = 4;
		
		MatadorMain.init(debugMode ? debugPlayers : Integer.valueOf(args[0]), debugMode ? debugAIPlayers : Integer.valueOf(args[1]));
		MatadorMain.startGameLoop();
		MatadorMain.stop();
	}
}
