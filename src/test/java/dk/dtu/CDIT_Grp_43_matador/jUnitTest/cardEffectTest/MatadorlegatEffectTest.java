package dk.dtu.CDIT_Grp_43_matador.jUnitTest.cardEffectTest;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.MatadorlegatEffect;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatadorlegatEffectTest {

    @Test
    void matadorLegatTest() {
        MatadorlegatEffect matadorLegat = new MatadorlegatEffect(2000,750);
        Player p = new Player("Knud",1500);
        Player p1 = new Player("Bo",600);
        System.out.println("---- MatadorLegatEffectTest ----");
        System.out.println("---- Score før test ---- \n" + p + " score: " + p.getScore() + " og " + p1 + " score: " + p1.getScore());
        System.out.println(matadorLegat.print(p));
        matadorLegat.useEffect(p);
        System.out.println(matadorLegat.print(p1));
        matadorLegat.useEffect(p1);


        System.out.println("---- Score efter test ---- \n" + p + " score: " + p.getScore() + " og " + p1 + " score: " + p1.getScore());
        assertEquals(1500, p.getScore());
        assertEquals(2600, p1.getScore());
    }
}