package dk.dtu.CDIT_Grp_43_matador.jUnitTest.cardEffectTest;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.MoveToClosestShipEffect;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MoveToClosestShipEffectTest {

    @Test
    void useEffect() {
        try {
            GameController.getInstance().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameBoard bord = GameBoard.getInstance();
        try {
            bord.initBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Player p = new Player("testPlayer", 1500);
        System.out.println("\n---- MovePlayerToTest ----");
        System.out.println("---- placering før test ---- \n" + p + " placering: " + p.getCurrPos());
        MoveToClosestShipEffect moveToShip = new MoveToClosestShipEffect("Ship");
        p.move(6);
        System.out.println("we first move the player to position 6 one placement from a ship");
        moveToShip.useEffect(p);
        System.out.println("---- placering efter test ---- \n" + p + " placering: " + p.getCurrPos());
        assertTrue(p.getCurrPos() == 15);
        System.out.println("we then move the player to startpaint");
        p.move(-15);
        moveToShip.useEffect(p);
        System.out.println("---- placering efter test ---- \n" + p + " placering: " + p.getCurrPos());
        assertTrue(p.getCurrPos() == 5);
    }
}