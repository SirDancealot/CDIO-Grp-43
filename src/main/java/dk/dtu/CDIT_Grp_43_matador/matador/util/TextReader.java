package dk.dtu.CDIT_Grp_43_matador.matador.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class TextReader {

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
}