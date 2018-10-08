package matador;

public class Matador {
	public static void main(String[] args) {
		boolean defaultMode = args.length == 0;
		int defaultPlayers = 2;
		boolean defaultAIMode = !(args.length == 2);
		int defaultAIPlayers = 0;
		
		MatadorMain.init(defaultMode ? defaultPlayers : Integer.valueOf(args[0]), defaultAIMode ? defaultAIPlayers : Integer.valueOf(args[1]));
		MatadorMain.startGameLoop();
		MatadorMain.stop();
	}
}
