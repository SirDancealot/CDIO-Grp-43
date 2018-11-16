package dk.dtu.CDIT_Grp_43_matador.matador.language;

import java.io.IOException;

public class LanguageController {
	
	private static Lang currentLanguage;
	private final static String[] LANGS = { "eng", "da" };
	private static Lang[] initLangs = new Lang[LANGS.length];

	/**
	 * @return Returns the existing languages
	 */
	public static String[] getLangs() {
		return LANGS;
	}

	/**
	 * @return Returns the language that is currently selected
	 */
	public static Lang getCurrentLanguage() {
		return currentLanguage;
	}

	/**
	 * Initializes a new language and sets it as current language, should only be called once during initial initialization
	 * @param langIndex is the index of the language to be initialized from the array {@code LANGS}
	 * @throws IOException if an I/O error occurs.
	 */
    public static void initLang(int langIndex) throws IOException {
        currentLanguage = new Lang("res/lang/"+LANGS[langIndex]+".txt", LANGS[langIndex]);
        initLangs[langIndex] = currentLanguage;
	}
    
    /**
     * 
     * @param lang the string representation of the language to use, which is the name of the language file without the filetype .txt
     * @return Returns -1 if setting a new language failed or 1 if it was successful.
     * @throws IOException if an I/O error occurs.
     */
    public static int setNewLang(String lang) throws IOException {
    	int langIndex = -1;
    	for (int i = 0; i < LANGS.length; i++) { //Finds what index in the LANGS array the language that is being selected is
			if (lang == LANGS[i]) {
				langIndex = i;
				break;
			}
		}
    	if (langIndex == -1) //This means that there was found no language
    		return -1;
    	if (initLangs[langIndex] != null) { //Checks if the language already has been initialized and uses that instead of initializing a new one.
    		currentLanguage = initLangs[langIndex];
    		return 1;
    	}
    	currentLanguage = new Lang("res/language/"+LANGS[langIndex]+".txt", LANGS[langIndex]);
    	initLangs[langIndex] = currentLanguage;
    	return 1;
    }
}