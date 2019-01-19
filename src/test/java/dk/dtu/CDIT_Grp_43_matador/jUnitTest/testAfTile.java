package dk.dtu.CDIT_Grp_43_matador.jUnitTest;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.Logic;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Brewery;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Property;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Ship;
import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class testAfTile {

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
        System.out.println("---- Test af GoToJail ----");
        GameBoard bord = GameBoard.getInstance();
        Logic logic = Logic.getINSTANCE();
        TextReader.init();
        try {
            bord.initBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Player[] p = {new Player("Knudbørge", 1500)};
        logic.init(p);

        Tile goToJailTile = bord.getGameTiles()[30];

        assertFalse(p[0].isInJail());
        goToJailTile.landOnTile(p[0]);
        assertTrue(p[0].isInJail());
    }
    @Test
    public void testAfFreeParking(){
        FreeParking parkingTile = new FreeParking("", "Tag:FreePark;name:FreeParking", 0);
        Player p = new Player("testPlayer", 20);
        int pos = p.getCurrPos();int score = p.getScore();int TO = p.getOwnedTiles().size();
        System.out.println("---- Før test position: "+ pos+" score: "+score+" tilesOwned: "+TO+" ----");

        parkingTile.landOnTile(p);

        int posA = p.getCurrPos();int scoreA = p.getScore();int TOA = p.getOwnedTiles().size();
        System.out.println("---- Efter test position: "+ posA+" score: "+scoreA+" tilesOwned: "+TOA+" ----");

        assertEquals(pos,posA);
        assertEquals(score,scoreA);
        assertEquals(TO,TOA);
    }
    @Test
    public void testAfProperty(){
        Property raadhusTile = new Property("Raadhuspladsen", "type:Property;Tilevalue:400;sister:brown;setSize:2;housePrice:200", 39, "0:50;1:200;2:600;3:1400;4:1700;5:2000");
        Property frederiksbergTile = new Property("Frederiksberggade", "type:Property;Tilevalue:350;sister:brown;setSize:2;housePrice:200", 37, "0:35;1:175;2:500;3:1100;4:1300;5:1500");

        Player p1 = new Player("testPlayer", 1500);
        Player p2 = new Player("testPlayer", 1500);
        System.out.println("---- testAfProperty ----");
        System.out.println("---- p1 tiles owned før test:" + " " +p1.getOwnedTiles().size()+" ----");
        System.out.println("---- p1 score før test"+ " "+p1.getScore()+ " "+ "----");
        System.out.println("---- p2 score før test "+ p2.getScore()+ " ----");
        int score = p1.getScore();
        int TO = p1.getOwnedTiles().size();
        assertTrue(TO==0);

        raadhusTile.buyTile(p1);
        frederiksbergTile.buyTile(p1);

        p1.setMoney(1500);
        raadhusTile.landOnTile(p2);

        TO = p1.getOwnedTiles().size();
        assertTrue(TO==2);
        assertFalse(score==p1.getScore());
        assertTrue(1500+100 == p1.getScore());
        assertTrue(1400 == p2.getScore());
        System.out.println("---- p1 tiles owned efter test " + p1.getOwnedTiles().size()+" ----");
        System.out.println("---- p1 score efter test "+ p1.getScore()+ " ----");
        System.out.println("---- p2 score efter test "+ p2.getScore()+ "----");


        raadhusTile.addHouseLevel();
        raadhusTile.addHouseLevel();

        raadhusTile.landOnTile(p2);
        assertEquals(800, p2.getScore());

        raadhusTile.landOnTile(p1);

        raadhusTile.removeHouseLevel();
        raadhusTile.landOnTile(p2);
        assertEquals(600, p2.getScore());
    }
    @Test
    public void testAfStart(){
        Start startTile = new Start("", "type:Start;passedValue:200;name:Start", 0);
        Player p = new Player("testPlayer", 1500);
        int score = p.getScore();
        p.setStartMoneyElegible(true);

        System.out.println("---- testAfPassedStart ----");
        System.out.println("---- p score før test " + p.getScore()+ " ----");
        startTile.passedTile(p);

        System.out.println("---- p score efter test " + p.getScore()+ " ----");
        assertFalse(score==p.getScore());

        p.setMoney(1500);
        System.out.println("---- testAfLandOnStart ----");
        System.out.println("---- p score før test " + p.getScore()+ " ----");
        startTile.landOnTile(p);
        System.out.println("---- p score efter test " + p.getScore()+ " ----");

        assertEquals(1700, p.getScore());

    }

    @Test
    public void testAfShip(){
        Player p = new Player("testPlayer",1500);
        Player p1 = new Player("testPlayer1", 1500);
        Ship shipTile = new Ship("", "type:Ship;Tilevalue:200;setSize:4;sister:ship;name:A/S Oresund", 5,"1:25;2:50;3:100;4:200" );
        Ship shipTile1 = new Ship("","type:Ship;Tilevalue:200;sister:ship;setSize:4;name:D.F.D.S.", 15, "1:25;2:50;3:100;4:200");

        System.out.println("---- testAfShip ----");
        System.out.println("---- p score før test " + p.getScore()+ " ----");
        System.out.println("---- p1 score før test " + p1.getScore()+ " ----");

        shipTile.buyTile(p1);
        p1.setMoney(1500);
        shipTile.landOnTile(p);

        System.out.println("---- p score efter test "+ p.getScore()+ " ----");
        System.out.println("---- p1 score efter test "+ p1.getScore()+ " ----");

        assertEquals(1500+25, p1.getScore());
        assertEquals(1500-25, p.getScore());

        shipTile1.buyTile(p1);
        p1.setMoney(1500);
        p.setMoney(1500);
        shipTile1.landOnTile(p);

        assertEquals((1500+50), p1.getScore());
        assertEquals((1500-50), p.getScore());


        shipTile.landOnTile(p1);

        assertEquals((1500+50), p1.getScore());
    }

    @Test
    public void testAfBrewery() {
        GameBoard board = GameBoard.getInstance();
        try{
            GameController.getInstance().init();
        } catch (Exception e){
            e.printStackTrace();
        }

        int roll = 12;
        int x4Price = roll * 4;
        int x10Price = roll * 10;

        Player p = new Player("testPlayer",1500);
        Player p1 = new Player("testPlayer1", 1500);
        Brewery breweryTile1 = new Brewery("","type:Brewery;Tilevalue:150;sister:brew;setSize:2;name:Carlsberg", 28, "1:4;2:10");
        Brewery breweryTile = new Brewery("", "type:Brewery;Tilevalue:150;sister:brew;setSize:2;name:Bryggeriet Tuborg", 12,"1:4;2:10");

        p.move(roll);

        System.out.println("---- testAfBrewery ----");
        System.out.println("---- test med roll 12 ----");
        System.out.println("---- p1 score før test "+p1.getScore()+" ----");
        System.out.println("---- p score før test "+p.getScore()+" ----");

        breweryTile.buyTile(p1);
        p1.setMoney(1500);
        breweryTile.landOnTile(p);


        assertTrue((1500+x4Price) == p1.getScore());
        assertTrue((1500-x4Price) == p.getScore());

        System.out.println("---- p1 score efter test "+p1.getScore()+" ----");
        System.out.println("---- p score efter test "+p.getScore()+ "----");

        breweryTile1.buyTile(p1);
        p1.setMoney(1500);
        p.setMoney(1500);
        breweryTile.landOnTile(p);

        assertEquals((1500+x10Price), p1.getScore());
        assertEquals((1500-x10Price), p.getScore());

        p1.setMoney(1500);
        breweryTile.landOnTile(p1);

        assertEquals(1500, p1.getScore());

    }
    @Test
    public void testAfTaxPercentValgPercent() {

        try {
            GameController.getInstance().init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Player p = new Player("testPlayer",3000);
        Tax taxTile = new Tax("","Tile4=type:Tax;money:200;percent:10;name:Tax Percent",4);

        System.out.println(p.getScore());

        taxTile.landOnTile(p);
        assertEquals(2700,p.getScore());

        System.out.println(p.getScore());

    }
}
