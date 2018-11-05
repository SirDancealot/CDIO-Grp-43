package dk.dtu.CDIT_Grp_43_matador.matador.language;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Lang {

	private String[] tokens;
	private HashMap<String, String> langTokens = new HashMap<String, String>(); 
	BufferedReader langReader;
	
	public Lang(String path) throws IOException {
		loadNewLauguage(path);
	}


	private void loadNewLauguage(String path) throws IOException {
		langReader = new BufferedReader(new FileReader(path));
		String str;
		while ((str = langReader.readLine()) != null) {
			tokens = str.split("=");
			langTokens.put(tokens[0], tokens[1]);
			System.out.println(tokens[0]);
		}
	}

	public String getTag(String tag) {
		return langTokens.get(tag);
	}
}