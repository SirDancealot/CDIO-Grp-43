package dk.dtu.CDIT_Grp_43_matador.jUnitTest;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.Tax;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class testAfTax {

    @Test
    public void landOnTile() {

        Player p = new Player("testPlayer",1500);
        Tax taxTile = new Tax("","type:Tax;money:100;name:Tax Flat",38);


        System.out.println(p.getScore());

        taxTile.landOnTile(p);

        System.out.println(p.getScore());

       assertTrue(1400 == p.getScore());

    }

    @Test
    public void printLandOn() {
    }

    @Test
    public void printPassed() {
    }
}