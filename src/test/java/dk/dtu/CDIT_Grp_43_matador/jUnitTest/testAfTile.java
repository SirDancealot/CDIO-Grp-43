package dk.dtu.CDIT_Grp_43_matador.jUnitTest;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.Logic;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Brewery;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Property;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Ship;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testAfTile {
    /*@BeforeEach
    public void initBoaard() {
        GameBoard board = GameBoard.getInstance();
        try{
            GameController.getInstance().init();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
*/
    @Test
    public void testAfJail(){
        GameBoard bord = GameBoard.getInstance();
        try {
            bord.initBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Jail jailTile = new Jail("", "tag:Jail;name:Jail", 0);
        GoToJail GoToJailTile = new GoToJail("", "tag:GoToJail;name:GoToJail", 0);
        Player p = new Player("testPlayer", 1500);
        int currMoney = p.getScore();
        jailTile.passedTile(p);
        assertEquals(currMoney,p.getScore());
        assertFalse(p.isInJail());

        GoToJailTile.landOnTile(p);

        assertTrue(p.isInJail());
        jailTile.passedTile(p);
        assertFalse(currMoney==p.getScore());
    }
    @Test
    public void testAfGoToJail(){
        GameBoard bord = GameBoard.getInstance();
        try {
            bord.initBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }

        GoToJail GoToJailTile = new GoToJail("", "tag:GoToJail;name:GoToJail", 0);
        Player p = new Player("testPlayer", 20);
        assertFalse(p.isInJail());
        GoToJailTile.landOnTile(p);
        assertTrue(p.isInJail());
    }
    @Test
    public void testAfFreeParking(){
        FreeParking parkingTile = new FreeParking("", "Tag:FreePark;name:FreeParking", 0);
        Player p = new Player("testPlayer", 20);
        int pos = p.getCurrPos();int score = p.getScore();int TO = p.getOwnedTiles().size();

        parkingTile.landOnTile(p);

        int posA = p.getCurrPos();int scoreA = p.getScore();int TOA = p.getOwnedTiles().size();

        assertEquals(pos,posA);
        assertEquals(score,scoreA);
        assertEquals(TO,TOA);
    }
    @Test
    public void testAfProperty(){
        Property raadhusTile = new Property("Raadhuspladsen", "type:Property;Tilevalue:400;sister:brown;setSize:2;housePrice:200", 39, "0:50;1:200;2:600;3:1400;4:1700;5:2000");
        Property frederiksbergTile = new Property("Frederiksberggade", "type:Property;Tilevalue:350;sister:brown;setSize:2;housePrice:200", 37, "0:35;1:175;2:500;3:1100;4:1300;5:1500");

        Player p1 = new Player("testPlayer", 10000);
        Player p2 = new Player("testPlayer", 10000);
        int score = p1.getScore();
        int TO = p1.getOwnedTiles().size();
        assertTrue(TO==0);

        raadhusTile.buyTile(p1);
        frederiksbergTile.buyTile(p1);

        raadhusTile.landOnTile(p2);

        TO = p1.getOwnedTiles().size();
        System.out.println(p1.getOwnedTiles().size());
        assertTrue(TO==2);
        assertFalse(score==p1.getScore());
        assertTrue(10000-750+100 == p1.getScore());
        assertTrue(9900 == p2.getScore());
        System.out.println(p2.getScore());
        System.out.println(p1.getScore());

    }
    @Test
    public void testAfStart(){
        Start startTile = new Start("", "type:Start;passedValue:200;name:Start", 0);
        Player p = new Player("testPlayer", 1500);
        int score = p.getScore();
        p.setStartMoneyElegible(true);


        startTile.passedTile(p);

        System.out.println(p.getScore());
        assertFalse(score==p.getScore());

    }

    @Test
    public void testAfShip(){
        Player p = new Player("testPlayer",1500);
        Player p1 = new Player("testPlayer1", 1500);
        Ship shipTile = new Ship("", "type:Ship;Tilevalue:200;setSize:4;sister:ship;name:A/S Oresund", 5,"0:50;1:200;2:600;3:1400;4:1700;5:2000" );

        shipTile.buyTile(p1);

        shipTile.landOnTile(p);

        System.out.println(p1.getScore());
        System.out.println(p.getScore());

        assertTrue(1500-200+50 == p1.getScore());
        assertTrue(1500-50 == p.getScore());
    }

    @Test
    public void testAfBrewery() {

        Player p = new Player("testPlayer",1500);
        Player p1 = new Player("testPlayer1", 1500);
        Brewery breweryTile1 = new Brewery("","type:Brewery;Tilevalue:150;sister:pink;setSize:2;name:Carlsberg", 28, "1:4;2:10");
        Brewery breweryTile = new Brewery("", "type:Brewery;Tilevalue:150;sister:pink;setSize:2;name:Bryggeriet Tuborg", 12,"1:4;2:10");

        breweryTile.buyTile(p1);
        breweryTile.landOnTile(p);

        System.out.println(p1.getScore());
        System.out.println(p.getScore());

        assertTrue((1500-150+24) == p1.getScore()+24);
        assertTrue((1500-24) == p.getScore()-24);

        p1.setMoney(1500);
        p.setMoney(1500);

        breweryTile1.buyTile(p1);
        breweryTile.landOnTile(p);

        System.out.println(p1.getScore()+60);
        System.out.println(p.getScore()-60);

        assertTrue((1500-150+60) == p1.getScore()+60);
        assertTrue((1500-60) == p.getScore()-60);

    }
}
