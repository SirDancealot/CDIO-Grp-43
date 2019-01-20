package dk.dtu.CDIT_Grp_43_matador.jUnitTest;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.Logic;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Bank;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Die;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Brewery;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Property;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Ship;
import dk.dtu.CDIT_Grp_43_matador.matador.util.TextReader;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.DiceCup;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class testAfTile {

    @Test
    public void testAfJail(){
        System.out.println("---- Test af Jail ----");
        Bank bank = Bank.getInstance();
        GameBoard board = GameBoard.getInstance();
        Logic logic = Logic.getINSTANCE();
        TextReader.init();
        DiceCup dc = DiceCup.getInstance();
        try {
            board.initBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Player[] p = {new Player("Michel (ikke Michael)", 7000)};
        int moneyBefore = p[0].getScore();
        logic.init(p);

        Tile Jail = board.getGameTiles()[10];
        assertFalse(p[0].isInJail());
        p[0].setInJail(true);
        assertTrue(p[0].isInJail());
        ((Jail) (board.getTileByName("Jail"))).payToExit(p[0]);
        assertFalse(p[0].isInJail());
        assertFalse(p[0].getScore() == moneyBefore);
        System.out.println(p[0].getName()+ " havde: " + moneyBefore + ", og har nu: " + p[0].getScore() + " efter at have betalt for at komme ud");
        p[0].setInJail(true);

        /*dc.setSame();
        if(dc.isSame()) {
            p[0].setInJail(false);
            System.out.println("Spilleren slog 2 ens og er stadig i fængsel: " + p[0].isInJail());
        }
        assertTrue(!p[0].isInJail());*/


        Die die1 = new Die(1);
        Die die2 = new Die(1);
        for(int i = 0; i < 3; i++) {
            dc.roll();
            System.out.println("Terning 1 slog: " + die1.getFaceValue() + " Og terning 2 slog: " + die2.getFaceValue());
            if(dc.threeSame()){
                p[0].setInJail(true);
            }
        }
        assertTrue(p[0].isInJail());
    }

    @Test
    public void testAfGoToJail(){
        GameBoard bord = GameBoard.getInstance();
        try {
            bord.initBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }

        GoToJail GoToJailTile = new GoToJail("", "tag:GoToJail;name:GoToJail", 30);
        Player p = new Player("testPlayer", 1500);
        assertFalse(p.isInJail());
        GoToJailTile.landOnTile(p);
        assertTrue(p.isInJail());
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
        TextReader.init();
        GameBoard board = GameBoard.getInstance();
        Logic logic = Logic.getINSTANCE();
        try {
            board.initBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Player[] p = {new Player("testPlayer", 1500),new Player("testPlayer1",1500)};
        logic.init(p);

        int roll = 12;
        int x4Price = roll * 4;
        int x10Price = roll * 10;

        Tile breweryTile = board.getGameTiles()[28];
        Tile breweryTile1 = board.getGameTiles()[12];

        p[0].move(roll);

        System.out.println("---- testAfBrewery ----");
        System.out.println("---- test med roll 12 ----");
        System.out.println("---- p1 score før test "+p[1].getScore()+" ----");
        System.out.println("---- p score før test "+p[0].getScore()+" ----");

        ((Ownable)breweryTile).buyTile(p[1]);
        p[1].setMoney(1500);
        breweryTile.landOnTile(p[0]);


        assertTrue((1500+x4Price) == p[1].getScore());
        assertTrue((1500-x4Price) == p[0].getScore());

        System.out.println("---- p1 score efter test "+p[1].getScore()+" ----");
        System.out.println("---- p score efter test "+p[0].getScore()+ "----");

        ((Ownable)breweryTile1).buyTile(p[1]);
        p[1].setMoney(1500);
        p[0].setMoney(1500);
        breweryTile.landOnTile(p[0]);

        assertEquals((1500+x10Price), p[1].getScore());
        assertEquals((1500-x10Price), p[0].getScore());

        p[1].setMoney(1500);
        breweryTile.landOnTile(p[1]);

        assertEquals(1500, p[1].getScore());

    }
    @Test
    public void testAfTaxPercentValgPercent() {
        TextReader.init();
        GameBoard gameboard = GameBoard.getInstance();
        Logic logic = Logic.getINSTANCE();
        try {
            gameboard.initBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Player[] p = {new Player("testPlayer", 1500)};
        logic.init(p);

        Tile taxTile = new Tax("", "type:Tax;percent:10;name:Tax Percent",4);
        Tile taxTile1 = new Tax("", "type:Tax;money:200;name:Tax Percent",4);

        System.out.println("Test af Tax tile");
        System.out.println("spillers score før 10% fratrukket "+p[0].getScore());

        taxTile.landOnTile(p[0]);
        assertEquals(1350,p[0].getScore());

        System.out.println("spillers score efter 10% fratrukket "+p[0].getScore());

        p[0].setMoney(1500);

        System.out.println("Test af Tax tile");
        System.out.println("spillers score før 200 kr fratrukket "+p[0].getScore());

        taxTile1.landOnTile(p[0]);

        System.out.println("spillers score efter 200 kr fratrukket "+p[0].getScore());

        assertEquals(1300, p[0].getScore());

    }
}
