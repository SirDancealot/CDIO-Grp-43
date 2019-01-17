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
        System.out.println("\n---- MovePlayerTest ----");
        System.out.println("---- placering før test ---- \n" + p + " placering: " + p.getCurrPos());
        System.out.println(movePlayer.print(p));
        movePlayer.useEffect(p);
        System.out.println("---- placering efter test ---- \n" + p + " placering: " + p.getCurrPos());
        assertTrue(p.getCurrPos() == 37);
        MovePlayerEffect movePlayer1 = new MovePlayerEffect(3);
        System.out.println(movePlayer1.print(p));
        movePlayer1.useEffect(p);
        assertTrue(p.getCurrPos() == 0);
        System.out.println("---- placering efter test ---- \n" + p + " placering: " + p.getCurrPos());
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
        System.out.println("\n---- MovePlayerToTest ----");
        System.out.println("---- placering før test ---- \n" + p + " placering: " + p.getCurrPos());
        System.out.println(movePlayerTo.print(p));
        movePlayerTo.useEffect(p);
        System.out.println("---- placering efter test ---- \n" + p + " placering: " + p.getCurrPos());

        assertTrue(p.getCurrPos() == 6);
        MovePlayerToEffect movePlayerTo1 = new MovePlayerToEffect("Valby Langgade");
        System.out.println(movePlayerTo1.print(p));
        movePlayerTo1.useEffect(p);
        assertTrue(p.getCurrPos() == 8);
        System.out.println("---- placering efter test ---- \n" + p + " placering: " + p.getCurrPos());
    }
}