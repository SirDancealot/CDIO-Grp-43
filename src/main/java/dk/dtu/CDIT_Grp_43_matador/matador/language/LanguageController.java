package dk.dtu.CDIT_Grp_43_matador.matador.language;


public class LanguageController {
	
	private static NewLauguage english;
	private static String[] LANGS = { "eng" };
	
	public static void main(String[] args) {
		
		english = new NewLauguage("res/language/english.txt"); 
	}

	public static String[] getLangs() {
		return LANGS;
	}

	public static void initLang(int langIndex) {
		
	}
}

