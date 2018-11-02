package dk.dtu.CDIT_Grp_43_matador.matador;

import java.io.IOException;

import dk.dtu.CDIT_Grp_43_matador.matador.language.LanguageController;

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
		final String DEFAULTPLAYERS = "2";
		final String DEFAULTAIPLAYERS = "0";
		final String DEFAULTLANG = LanguageController.getLangs()[0];

		String[] settings = { DEFAULTPLAYERS, DEFAULTAIPLAYERS, DEFAULTLANG };
		try {
			
		for (int i = 0; i < args.length; i++) {
			settings[i] = args[i];
		}
		} catch (Exception e){
			
		}
		
		
		Matador.init(settings);
		Matador.startGameLoop();
		Matador.stop();
	}
}
