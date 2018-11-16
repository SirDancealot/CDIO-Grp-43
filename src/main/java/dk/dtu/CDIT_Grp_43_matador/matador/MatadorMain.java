package dk.dtu.CDIT_Grp_43_matador.matador;

import java.io.IOException;

import dk.dtu.CDIT_Grp_43_matador.matador.language.*;

public class MatadorMain {
	/**
	 * The function that runs when the program is executed, it registrers wheter any arguments are given, 
	 * then initializes the game with given arguments if there are any, otherwise it uses the default values, 
	 * then the main game loop is started, and when that exits it closes the program
	 * @param args the settitings registrerd when the program is launched.
	 * @throws IOException if an I/O error occurs.
	 */

	public static void main(String[] args) throws IOException {
		final String[] SUPPORTEDLANGS = LanguageController.getLangs();
		final String DEFAULTPLAYERS = "2";
		final String DEFAULTAIPLAYERS = "0";
		final String DEFAULTLANG = SUPPORTEDLANGS[0];
		final String DEFAULTTPS = "20";


		String[] settings = { DEFAULTPLAYERS, DEFAULTAIPLAYERS, DEFAULTLANG, DEFAULTTPS };
		try {
			for (int i = 0; i < args.length; i++) {
				settings[i] = args[i];
			}
		} catch (Exception e){
			String supportedLangString = "";
			for (String string : SUPPORTEDLANGS) {
				supportedLangString += string + " ";
			}
			
			System.out.println("There was entered too many arguments when launching the program, the format is:\n"
					+ "\tjava -jar Matador.jar <Players> <AI's> <Lang> <tps>\n"
					+ "Currently supported languages are: " + supportedLangString + "\n"
					+ "The game will now launch with default settings");
		}
		
		
		Matador.init(settings);
		Matador.startGameLoop();
		Matador.stop();
	}
}
