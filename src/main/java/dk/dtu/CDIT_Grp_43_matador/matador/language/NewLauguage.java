package dk.dtu.CDIT_Grp_43_matador.matador.language;

import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;

public class NewLauguage {

	private String[] tokens;
	
	public NewLauguage(String path) {
		
		loadNewLauguage(path);
	}

	public String[] getTokens() {
		return tokens;
	}

	private void loadNewLauguage(String path) {
		
		String file = TextReader.loadFileAsString(path);
		tokens = file.split(";");
	}



}