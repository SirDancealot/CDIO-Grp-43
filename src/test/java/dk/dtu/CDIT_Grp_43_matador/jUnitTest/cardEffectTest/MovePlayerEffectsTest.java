package dk.dtu.CDIT_Grp_43_matador.jUnitTest.cardEffectTest;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.Logic;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.MovePlayerEffect;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.cardEffects.MovePlayerToEffect;
import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MovePlayerEffectsTest {

    @Test
    void useEffectMovePlayer() {
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

        MovePlayerEffect movePlayer = new MovePlayerEffect(-3);
        System.out.println("\n---- MovePlayerTest ----");
        System.out.println("---- placering før test ---- \n" + p[0] + " placering: " + p[0].getCurrPos());
        System.out.println(movePlayer.print(p[0]));
        movePlayer.useEffect(p[0]);
        System.out.println("---- placering efter test ---- \n" + p[0] + " placering: " + p[0].getCurrPos());
        assertTrue(p[0].getCurrPos() == 37);
        MovePlayerEffect movePlayer1 = new MovePlayerEffect(3);
        System.out.println(movePlayer1.print(p[0]));
        movePlayer1.useEffect(p[0]);
        assertTrue(p[0].getCurrPos() == 0);
        System.out.println("---- placering efter test ---- \n" + p[0] + " placering: " + p[0].getCurrPos());
    }

    @Test
    void useEffectMovePlayerTo() {
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


        MovePlayerToEffect movePlayerTo = new MovePlayerToEffect("Roskildevej");
        System.out.println("\n---- MovePlayerToTest ----");
        System.out.println("---- placering før test ---- \n" + p + " placering: " + p[0].getCurrPos());
        System.out.println(movePlayerTo.print(p[0]));
        movePlayerTo.useEffect(p[0]);
        System.out.println("---- placering efter test ---- \n" + p[0] + " placering: " + p[0].getCurrPos());

        assertTrue(p[0].getCurrPos() == 6);
        MovePlayerToEffect movePlayerTo1 = new MovePlayerToEffect("Valby Langgade");
        System.out.println(movePlayerTo1.print(p[0]));
        movePlayerTo1.useEffect(p[0]);
        assertTrue(p[0].getCurrPos() == 8);
        System.out.println("---- placering efter test ---- \n" + p + " placering: " + p[0].getCurrPos());
    }
}