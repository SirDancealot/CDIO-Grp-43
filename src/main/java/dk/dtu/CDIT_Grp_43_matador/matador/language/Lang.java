package dk.dtu.CDIT_Grp_43_matador.matador.language;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;


public class Lang {

//	private String[] tokens;
	private HashMap<String, String> langTokens = new HashMap<String, String>(); 
	BufferedReader langReader;
	private String lang;
	
	/**
	 * @param path is the path of the language file for this language
	 * @param lang is what language this object is initialized as
	 * @throws IOException if an I/O error occurs.
	 */
	public Lang(String path, String lang) throws IOException {
		this.langTokens = TextReader.fileToHashMap(path);
		//loadNewLauguage(path);
		this.lang = lang;
	}

	/**
	 * Stores all the strings to be printed in a {@code HashMap} under a uniform tag.
	 * @param path is the {@code String} representation of the location of the lang file
	 * @throws IOException
	 */
//	private void loadNewLauguage(String path) throws IOException {
//		langReader = new BufferedReader(new FileReader(path));
//		String str;
//		while ((str = langReader.readLine()) != null) {
//			tokens = str.split("=");
//			langTokens.put(tokens[0], tokens[1]);
//		}
//	}

	/**
	 * @param tag is the tag of the string to be printed, where the string differ from language to language but the tag does not
	 * @return Returns the string to be printed in the corresponding language
	 */
	public String getTag(String tag) {
		return langTokens.get(tag);
	}
	
	public String getLang() {
		return lang;
	}








}