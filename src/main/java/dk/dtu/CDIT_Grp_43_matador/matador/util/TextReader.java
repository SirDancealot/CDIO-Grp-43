package main.java.dk.dtu.CDIT_Grp_43_matador.matador.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TextReader {

	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path)); 
			String line; 
			while((line = br.readLine()) !=null) {
				builder.append(line + "\n");
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
}


