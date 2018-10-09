package unitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DiceCup.roll;

public class DiceUnitTest {

	@Test
	public void test() {
		
		roll testDice = new roll();
				
		final int totalRolls = 1000;
		
		int two;
		int three;
		int four;
		int five;
		int six;
		int seven;
		int eight;
		int nine;
		int ten;
		int eleven;
		int twelve;
		
		for (int i = 0; i < totalRolls; i++) {
			
			roll = diceTest.roll();
				switch (roll) {
                case 2:
                    two++;
                    break;
                case 3:
                    three++;
                    break;
                case 4:
                    four++;
                    break;
                case 5:
                    five++;
                    break;
                case 6:
                    six++;
                    break;
                case 7:
                    seven++;
                    break;
                case 8:
                    eight++;
                    break;
                case 9:
                    nine++;
                    break;
                case 10:
                    ten++;
                    break;
                case 11:
                    eleven++;
                    break;
                case 12:
                	twelve++;
                    break;
				}	
				
				System.out.println("The die rolled two: " + two + " times");
				System.out.println("The die rolled three: " + three + " times");
				System.out.println("The die rolled four: " + four + " times");
				System.out.println("The die rolled five: " + five + " times");
				System.out.println("The die rolled six: " + six + " times");
				System.out.println("The die rolled siven: " + seven + " times");
				System.out.println("The die rolled eight: " + eight + " times");
				System.out.println("The die rolled nine: " + nine + " times");
				System.out.println("The die rolled ten: " + ten + " times");
				System.out.println("The die rolled eleven: " + eleven + " times");
				System.out.println("The die rolled twelve: " + twelve + " times");
				
		}
		
		
	}

}
