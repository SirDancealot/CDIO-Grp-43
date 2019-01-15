package dk.dtu.CDIT_Grp_43_matador.jUnitTest.cardEffectTest;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.MovePlayerEffect;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.MovePlayerToEffect;
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
        MovePlayerEffect movePlayer = new MovePlayerEffect(-3);
        movePlayer.useEffect(p);
        assertTrue(p.getCurrPos() == 37);
        MovePlayerEffect movePlayer1 = new MovePlayerEffect(3);
        movePlayer1.useEffect(p);
        assertTrue(p.getCurrPos() == 0);
    }

    @Test
    void useEffectMovePlayerTo() {
        try {
            GameController.getInstance().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Player p = new Player("Malte", 1500);
        MovePlayerToEffect movePlayerTo = new MovePlayerToEffect("Roskildevej");
        movePlayerTo.useEffect(p);
        assertTrue(p.getCurrPos() == 6);
        MovePlayerToEffect movePlayerTo1 = new MovePlayerToEffect("Valby Langgade");
        movePlayerTo.useEffect(p);


        assertTrue(p.getCurrPos() == 8);
    }
}