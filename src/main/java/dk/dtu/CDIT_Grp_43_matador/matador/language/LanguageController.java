package dk.dtu.CDIT_Grp_43_matador.matador.language;

import java.io.IOException;

public class LanguageController {
	
	private static Lang currentLanguage;
	private final static String[] LANGS = { "eng", "da"  };
	private static Lang[] initLangs = new Lang[LANGS.length];

	public static String[] getLangs() {
		return LANGS;
	}

	public static Lang getCurrentLanguage() {
		return currentLanguage;

	}

    public static void initLang(int langIndex) throws IOException {
        currentLanguage = new Lang("res/lang/"+LANGS[langIndex]+".txt");
        initLangs[langIndex] = currentLanguage;
	}
    
    public static int setNewLang(String lang) throws IOException {
    	int langIndex = -1;
    	for (int i = 0; i < LANGS.length; i++) {
			if (lang == LANGS[i]) {
				langIndex = i;
				break;
			}
		}
    	if (langIndex == -1)
    		return -1;
    	if (initLangs[langIndex] != null) {
    		currentLanguage = initLangs[langIndex];
    		return 1;
    	}
    	currentLanguage = new Lang("res/language/"+LANGS[langIndex]+".txt");
    	initLangs[langIndex] = currentLanguage;
    	return 1;
    }
}