package dk.dtu.CDIT_Grp_43_matador.matador;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MatadorMainBalanceTest {

    int games = 1000;
    double high = (games/2)*1.05;
    double low = (games/2)*0.95;

    int p1 = 0;
    int p2 = 0;


    @RepeatedTest(5)
    public void winningTest() throws IOException {

        for (int i = 0; i < 1000; i++) {



        }
        assertTrue(low<p1 && p1<high,"p1 won " + p1 + "times and p2 won " + p2 + "times");
    }
}