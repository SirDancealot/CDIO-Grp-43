package dk.dtu.CDIT_Grp_43_matador.matador;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MatadorMainTestWinRate {

    int games = 1000;
    double high = (games/2)*1.05;
    double low = (games/2)*0.95;

    int p1 = 0;
    int p2 = 0;


    public void rungame() throws IOException {
        Matador.resetGame();
        Matador.init(new String[] {"0" , "2"});
        Matador.startGameLoop();
    }

    @RepeatedTest(5)
    public void winningTest() throws IOException {

        for (int i = 0; i < 1000; i++) {
            rungame();
            if(Matador.getCurrPlayer() == 0){
                p1++;
            } else {
                p2++;
            }
        }
        assertTrue(low<p1 && p1<high,"p1 won " + p1 + "times and p2 won " + p2 + "times");
    }
}