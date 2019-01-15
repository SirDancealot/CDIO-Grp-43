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
        MoveToClosestShipEffect moveToShip = new MoveToClosestShipEffect("Ship");

        moveToShip.useEffect(p);

        assertTrue(p.getCurrPos() == 5);

    }
}