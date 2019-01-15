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
        matadorLegat.useEffect(p);
        matadorLegat.useEffect(p1);
        assertEquals(1500, p.getScore());
        assertEquals(2600, p1.getScore());
    }
}