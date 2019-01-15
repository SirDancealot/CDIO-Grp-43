package dk.dtu.CDIT_Grp_43_matador.jUnitTest.cardEffectTest;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MovePlayerEffectsTest {

    @Test
    void useEffectMovePlayer() {
        try {
            GameController.getInstance().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Player p = new Player("Heidi", 1500);
        Player p1 = new Player("Hjalte", 1500);
        p.move(-3);
        assertTrue(p.getCurrPos() == 37);
        p.move(3);
        assertTrue(p.getCurrPos() == 0);
    }

    @Test
    void useEffectMovePlayerTo() {
        try {
            GameController.getInstance().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Player p = new Player("Heidi", 1500);
        Player p1 = new Player("Malte", 1500);
        p.moveTo("Roskildevej");
        assertTrue(p.getCurrPos() == 6);
        p.moveTo("Valby Langgade");
        assertTrue(p.getCurrPos() == 8);

    }
}