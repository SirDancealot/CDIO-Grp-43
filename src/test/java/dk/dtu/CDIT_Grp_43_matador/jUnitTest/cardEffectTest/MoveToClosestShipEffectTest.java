package dk.dtu.CDIT_Grp_43_matador.jUnitTest.cardEffectTest;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.Logic;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.MoveToClosestShipEffect;
import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MoveToClosestShipEffectTest {

    @Test
    void useEffect() {
        TextReader.init();
        Logic logic = Logic.getINSTANCE();
        GameBoard g = GameBoard.getInstance();
        try {
            g.initBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Player[] p = {new Player("testPlayer", 1500)};
        logic.init(p);

        System.out.println("\n---- MovePlayerToTest ----");
        System.out.println("---- placering før test ---- \n" + p[0] + " placering: " + p[0].getCurrPos());
        MoveToClosestShipEffect moveToShip = new MoveToClosestShipEffect("Ship");
        p[0].move(6);
        System.out.println("we first move the player to position 6 one placement from a ship");
        moveToShip.useEffect(p[0]);
        System.out.println("---- placering efter test ---- \n" + p[0] + " placering: " + p[0].getCurrPos());
        assertTrue(p[0].getCurrPos() == 15);
        System.out.println("we then move the player to startpaint");
        p[0].move(-15);
        moveToShip.useEffect(p[0]);
        System.out.println("---- placering efter test ---- \n" + p[0] + " placering: " + p[0].getCurrPos());
        assertTrue(p[0].getCurrPos() == 5);
    }
}