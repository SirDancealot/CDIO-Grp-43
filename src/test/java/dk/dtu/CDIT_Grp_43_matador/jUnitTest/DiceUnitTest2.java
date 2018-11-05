package dk.dtu.CDIT_Grp_43_matador.jUnitTest;




import org.junit.jupiter.api.RepeatedTest;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.DiceCup;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DiceUnitTest2 {



	@RepeatedTest(100)
	public void test() {
		final int totalRolls = 100000;
		final double thirtySixths = (double)totalRolls / 36.0;
		final double lowRoll = thirtySixths * 0.96;
		final double highRoll = thirtySixths * 1.04;
		
		int[] totalEyes;
		totalEyes = new int[11];
		
		for (int i = 0; i < totalRolls ; i++) {
			
			totalEyes[DiceCup.roll()-2]++;	
		}
		
		for (int i = 0; i < totalEyes.length; i++) {
			System.out.println("The dice rolled " + (i+2) + ": " + totalEyes[i] + " times");	
		}
		
		assertTrue((lowRoll * 1) <= totalEyes[0] && totalEyes[0] <= (highRoll * 1), "Wrong output recieved with 2's output was " + totalEyes[0] + " but should had been between " + (lowRoll * 1) + " and " + (highRoll * 1));
		assertTrue((lowRoll * 2) <= totalEyes[1] && totalEyes[1] <= (highRoll * 2), "Wrong output recieved with 3's output was " + totalEyes[1] + " but should had been between " + (lowRoll * 2) + " and " + (highRoll * 2));
		assertTrue((lowRoll * 3) <= totalEyes[2] && totalEyes[2] <= (highRoll * 3), "Wrong output recieved with 4's output was " + totalEyes[2] + " but should had been between " + (lowRoll * 3) + " and " + (highRoll * 3));
		assertTrue((lowRoll * 4) <= totalEyes[3] && totalEyes[3] <= (highRoll * 4), "Wrong output recieved with 5's output was " + totalEyes[3] + " but should had been between " + (lowRoll * 4) + " and " + (highRoll * 4));
		assertTrue((lowRoll * 5) <= totalEyes[4] && totalEyes[4] <= (highRoll * 5), "Wrong output recieved with 6's output was " + totalEyes[4] + " but should had been between " + (lowRoll * 5) + " and " + (highRoll * 5));
		assertTrue((lowRoll * 6) <= totalEyes[5] && totalEyes[5] <= (highRoll * 6), "Wrong output recieved with 7's output was " + totalEyes[5] + " but should had been between " + (lowRoll * 6) + " and " + (highRoll * 6));
		assertTrue((lowRoll * 5) <= totalEyes[6] && totalEyes[6] <= (highRoll * 5), "Wrong output recieved with 8's output was " + totalEyes[6] + " but should had been between " + (lowRoll * 5) + " and " + (highRoll * 5));
		assertTrue((lowRoll * 4) <= totalEyes[7] && totalEyes[7] <= (highRoll * 4), "Wrong output recieved with 9's output was " + totalEyes[7] + " but should had been between " + (lowRoll * 4) + " and " + (highRoll * 4));
		assertTrue((lowRoll * 3) <= totalEyes[8] && totalEyes[8] <= (highRoll * 3), "Wrong output recieved with 10's output was " + totalEyes[8] + " but should had been between " + (lowRoll * 3) + " and " + (highRoll * 3));
		assertTrue((lowRoll * 2) <= totalEyes[9] && totalEyes[9] <= (highRoll * 2), "Wrong output recieved with 11's output was " + totalEyes[9] + " but should had been between " + (lowRoll * 2) + " and " + (highRoll * 2));
		assertTrue((lowRoll * 1) <= totalEyes[10] && totalEyes[10] <= (highRoll * 1), "Wrong output recieved with 12's output was " + totalEyes[10] + " but should had been between " + (lowRoll * 1) + " and " + (highRoll * 1));
	}


}
