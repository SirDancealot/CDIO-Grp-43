package dk.dtu.CDIT_Grp_43_matador.matador.language;

import java.util.HashMap;

public class LanguageController {
	
	private static NewLauguage currentLanguage;
	private static String[] LANGS = { "eng", "da"  };
	private static String[] gameLines;
    private static HashMap<String, String> hmap;


	public static String[] getLangs() {
		return LANGS;
	}

    public static HashMap<String, String> getHmap() {
        return hmap;
    }

    public static void initLang(int langIndex) {

        currentLanguage = new NewLauguage("res/language/"+LANGS[langIndex]+".txt");
        gameLines = new String[currentLanguage.getTokens().length/2];

        int index = 0;
        for (int i = 1; i < currentLanguage.getTokens().length; i++) {
            if(i%2 == 1){
                gameLines[index] = currentLanguage.getTokens()[i];
                index ++;
            }
        }

        hmap = new HashMap<String, String>();
        hmap.put("enterPlayerName", gameLines[0]);
        hmap.put("playerRolling", gameLines[1]);
        hmap.put("turnRoll", gameLines[2]);
        hmap.put("enterRoll", gameLines[3]);
        hmap.put("playerRolled", gameLines[4]);
        hmap.put("rolledResult", gameLines[5]);
        hmap.put("playerTotalScore", gameLines[6]);
        hmap.put("doubleOnReset", gameLines[7]);
        hmap.put("sixesWin", gameLines[8]);
        hmap.put("sixesAlmostWin", gameLines[9]);
        hmap.put("additionalRoll", gameLines[10]);
	}
}

