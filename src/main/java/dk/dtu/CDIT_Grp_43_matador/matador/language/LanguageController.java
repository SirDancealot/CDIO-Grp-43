package main.java.dk.dtu.CDIT_Grp_43_matador.matador.language;


import main.java.dk.dtu.CDIT_Grp_43_matador.matador.language.*;
import main.java.dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.*;
import main.java.dk.dtu.CDIT_Grp_43_matador.matador.entity.*;
import main.java.dk.dtu.CDIT_Grp_43_matador.matador.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;


public class LanguageController {
	
	private static NewLauguage currentLanguage;
	private static String[] LANGS = { "english.txt", "danish.txt"  };
	private static String[] gameLines;
    private static HashMap<String, String> hmap;

	public static String[] getLangs() {
		return LANGS;
	}

    public static HashMap<String, String> getHmap() {
        return hmap;
    }

    public static void initLang(int langIndex) {

        currentLanguage = new NewLauguage("res/language/"+LANGS[langIndex]);
        gameLines = new String[currentLanguage.getTokens().length/2];

        int index = 0;
        for (int i = 1; i < currentLanguage.getTokens().length; i++) {
            if(i%2 == 1){
                gameLines[index] = currentLanguage.getTokens()[i];
                index ++;
            }
        }

        hmap = new HashMap<String, String>();

        hmap.put("playerRolling", gameLines[0]);
        hmap.put("turnRoll", gameLines[1]);
        hmap.put("enterRoll", gameLines[2]);
        hmap.put("playerRolled", gameLines[3]);
        hmap.put("rolledResult", gameLines[4]);
        hmap.put("playerTotalScore", gameLines[5]);
        hmap.put("doubleOnReset", gameLines[6]);
        hmap.put("sixesWin", gameLines[7]);
        hmap.put("sixesAlmostWin", gameLines[8]);
        hmap.put("additionalRoll", gameLines[9]);
	}
}

