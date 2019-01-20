package dk.dtu.CDIT_Grp_43_matador.jUnitTest;

import dk.dtu.CDIT_Grp_43_matador.matador.Logic;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Ownable;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Property;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tile;
import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Bank;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    @Test
    void updowngradeGround() {
        System.out.println("---- Test af upgradeGround ----");
        Bank bank = Bank.getInstance();
        GameBoard board = GameBoard.getInstance();
        Logic logic = Logic.getINSTANCE();
        TextReader.init();
        try {
            board.initBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Player[] p = {new Player("Michel (ikke Michael)", 7000)};
        logic.init(p);
        Tile rodovrevej = board.getGameTiles()[1];
        Tile hvidovre = board.getGameTiles()[3];
        ((Ownable)rodovrevej).buyTile(p[0]);
        ((Ownable)hvidovre).buyTile(p[0]);


        for (int i = 0; i < 5; i++) {
            bank.upgradeGround(p[0], rodovrevej);
            bank.upgradeGround(p[0], hvidovre);
            assertTrue(((Property)rodovrevej).getHouseLevel() == i+1);

            System.out.println("Der er nu: "+((Property)rodovrevej).getHouseLevel() + " huse/hoteller på Rødovrevej og "+((Property)hvidovre).getHouseLevel()+ " huse/hoteller på Hvidovrevej");
        }

        System.out.println("\n---- Test af downgradeGround ----");
        System.out.println("Der er nu: "+((Property)rodovrevej).getHouseLevel() + " huse/hoteller på Rødovrevej og "+((Property)hvidovre).getHouseLevel()+ " huse/hoteller på Hvidovrevej");
        for (int i = 0; i < 5; i++) {
            bank.downgradeGround(p[0], rodovrevej);
            bank.downgradeGround(p[0], hvidovre);
            assertTrue(((Property)rodovrevej).getHouseLevel() == 4-i);

            System.out.println("Der er nu: "+((Property)rodovrevej).getHouseLevel() + " huse/hoteller på Rødovrevej og "+((Property)hvidovre).getHouseLevel()+ " huse/hoteller på Hvidovrevej");
        }
    }

    @Test
    void PawnTile() {
        System.out.println("---- Test af pawnedTile() ----");
        Bank bank = Bank.getInstance();
        Player p = new Player("Knud", 1500);
        int scoreBefore = p.getScore();
        Property kgsnytorv = new Property("Kgs. Nytorv", "type:Property;Tilevalue:260;name:Kgs. Nytorv", 1, "0:22;1:110;2:330;3:800;4:975;5:1150");
        kgsnytorv.buyTile(p);
        System.out.println(p + " ejer denne tile: " + p.getOwnedTiles());
        bank.pawnTile(p, kgsnytorv);
        assertTrue(kgsnytorv.isPawned(), "Kgs. nytorv is pawned = " + kgsnytorv.isPawned());

        assertTrue(p.getScore() < scoreBefore);
        System.out.println(p + " har nuværende score: " + p.getScore() + ", og tidligere score var: " + scoreBefore + " er denne grund pantsat: " + kgsnytorv.isPawned());

        System.out.println("---- Test af unpawnedTile() ----");
        bank.unPawnTile(p, kgsnytorv);
        assertFalse(kgsnytorv.isPawned());
        System.out.println("Er " + p.getOwnedTiles() + " pantsat: " + kgsnytorv.isPawned());
    }
}