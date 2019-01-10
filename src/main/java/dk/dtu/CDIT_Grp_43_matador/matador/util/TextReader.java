package dk.dtu.CDIT_Grp_43_matador.matador.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class TextReader {
	private static HashMap<String, String> rent;
	private static HashMap<String, String> tiles;
	private static HashMap<String, String> cards;

	public void init() {
		try {
			rent = fileToHashMap("./res/Rent.txt");
			tiles = fileToHashMap("./res/Tiles.txt");
			cards = fileToHashMap("./res/Cards.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<String, String> fileToHashMap(String path) throws IOException {
		BufferedReader fileReader = new BufferedReader(new FileReader(path));
		HashMap<String, String> fileMap = new HashMap<String, String>();
		String str;
		
		while ((str = fileReader.readLine()) != null) {
			String[] tokens = str.split("=");
			fileMap.put(tokens[0], tokens[1]);
		}
		fileReader.close();
		return fileMap;
	}

	public static HashMap<String, String> getCards() {
		return cards;
	}

	public static HashMap<String, String> getRent() {
		return rent;
	}

	public static HashMap<String, String> getTiles() {
		return tiles;
	}
}