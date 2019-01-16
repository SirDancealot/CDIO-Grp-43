package dk.dtu.CDIT_Grp_43_matador.jUnitTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.RepeatedTest;

import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.DiceCup;

public class DiceUnitTest {
    @RepeatedTest(100)
    public void test() {
        final DiceCup dc = DiceCup.getInstance();
        final int totalRolls = 100000;
        final double sixths = (double) totalRolls / 12.0;
        final double lowRoll = sixths * 0.95;
        final double highRoll = sixths * 1.05;

        int[] totalEyes;
        totalEyes = new int[12];

        for (int i = 0; i < totalRolls; i++) {

            totalEyes[dc.roll() - 1]++;
        }

        for (int i = 0; i < totalEyes.length; i++) {
            System.out.println("The dice rolled " + (i + 2) + ": " + totalEyes[i] + " times");
        }

        assertTrue((lowRoll * 1) <= totalEyes[0] && totalEyes[0] <= (highRoll * 1), "Wrong output recieved with 2's output was " + totalEyes[0] + " but should had been between " + (lowRoll * 1) + " and " + (highRoll * 1));
        assertTrue((lowRoll * 1) <= totalEyes[1] && totalEyes[1] <= (highRoll * 1), "Wrong output recieved with 3's output was " + totalEyes[1] + " but should had been between " + (lowRoll * 2) + " and " + (highRoll * 2));
        assertTrue((lowRoll * 1) <= totalEyes[2] && totalEyes[2] <= (highRoll * 1), "Wrong output recieved with 4's output was " + totalEyes[2] + " but should had been between " + (lowRoll * 3) + " and " + (highRoll * 3));
        assertTrue((lowRoll * 1) <= totalEyes[3] && totalEyes[3] <= (highRoll * 1), "Wrong output recieved with 5's output was " + totalEyes[3] + " but should had been between " + (lowRoll * 4) + " and " + (highRoll * 4));
        assertTrue((lowRoll * 1) <= totalEyes[4] && totalEyes[4] <= (highRoll * 1), "Wrong output recieved with 6's output was " + totalEyes[4] + " but should had been between " + (lowRoll * 5) + " and " + (highRoll * 5));
        assertTrue((lowRoll * 1) <= totalEyes[5] && totalEyes[5] <= (highRoll * 1), "Wrong output recieved with 7's output was " + totalEyes[5] + " but should had been between " + (lowRoll * 6) + " and " + (highRoll * 6));
    }
}
