package main.java.dk.dtu.CDIT_Grp_43_matador.matador.language;

import main.java.dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;

public class NewLauguage {
	
	public NewLauguage(String path) {
		
		loadNewLauguage(path);
	}
	
		private void loadNewLauguage(String path) {
		
		String file = TextReader.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		for (int i = 0; i < tokens.length; i++) {
			System.out.println(tokens[i]);
		}
		
	}

}