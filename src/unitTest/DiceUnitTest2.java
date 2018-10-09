package unitTest;

import org.junit.jupiter.api.Test;
import wrapperClasses.DiceCup;

public class DiceUnitTest2 {
	
	@Test
	public void test() {
		
		final int totalRolls = 1000;
		int[] TotalEyes;
		TotalEyes = new int[11];
		for (int i = 0; i < totalRolls ; i++) {
			
			TotalEyes[DiceCup.roll()-2]++;	
		}
		
		for (int i = 0; i < TotalEyes.length; i++) {
			System.out.println("The dice rolled " + (i+2) + ": " + TotalEyes[i] + " times");	
		}	
	}	
}
