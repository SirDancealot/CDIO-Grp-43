package matador.wrapperClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class CustomStreamTokenizer {
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	public static final int TT_EOF = StreamTokenizer.TT_EOF;
	public static final int TT_EOL = StreamTokenizer.TT_EOL;
	public static final int TT_WORD = StreamTokenizer.TT_WORD;
	public static final int TT_NUMBER = StreamTokenizer.TT_NUMBER;
	
	
	public static void initTokenizer() {
		st.eolIsSignificant(true);
	}
	
	private static int nextToken() throws IOException {
		return st.nextToken();
	}
	
	private static void nextLine() throws IOException {
		while (st.nextToken() != StreamTokenizer.TT_EOL) {
		}
	}
	
	public static String nextString() throws IOException {
		while (nextToken() != StreamTokenizer.TT_WORD) {
		}
		String sval = st.sval;
		nextLine();
		return sval;
	}
	
	public static void waitForInput() throws IOException {
		nextLine();
	}
	
	public static StreamTokenizer getStInstance() {
		return st;
	}
}
