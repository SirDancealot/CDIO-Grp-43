package dk.dtu.CDIT_Grp_43_matador.jUnitTest.cardEffectTest;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.PayAllEffect;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayAllEffectTest {

    @Test
    void useEffect() {

        Player p = new Player("Greg", 1500);
        Player p1 = new Player("Phillip", 1500);
        Player p2 = new Player("Niklas med k", 1500);
        Player[] players = {p, p1, p2};
        Player.setPlayers(players);

        PayAllEffect payAll = new PayAllEffect(25);
        payAll.useEffect(p);
        payAll.useEffect(p1);
        System.out.println(p.getScore());
        System.out.println(p1.getScore());
        System.out.println(p2.getScore());

        assertEquals(1475, p.getScore());
        assertEquals(1475, p1.getScore());
        assertEquals(1550, p2.getScore());
    }
}