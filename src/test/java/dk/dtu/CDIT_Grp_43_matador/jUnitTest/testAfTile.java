package dk.dtu.CDIT_Grp_43_matador.jUnitTest;

import dk.dtu.CDIT_Grp_43_matador.matador.GameController;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.*;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Property;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testAfTile {
    @BeforeEach
    public void initBoaard() {
        GameBoard board = GameBoard.getInstance();
        try{
            GameController.getInstance().init();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

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
        Property propertyTile = new Property("Raadhuspladsen", "type:Property;Tilevalue:400;sister:brown;setSize:2;housePrice:200", 39, "0:50;1:200;2:600;3:1400;4:1700;5:2000");
        Player p = new Player("testPlayer", 10000);
        int score = p.getScore();
        int TO = p.getOwnedTiles().size();
        assertTrue(TO==0);

        propertyTile.landOnTile(p);
        propertyTile.buyTile(p);

        TO = p.getOwnedTiles().size();
        assertTrue(TO==1);
        assertFalse(score==p.getScore());
        System.out.println(p.getScore());

    }
    @Test
    public void testAfStart(){
        Start startTile = new Start("", "tag:Start;passedValue:3;name:Start", 0);
        Player p = new Player("testPlayer", 20);
        int score = p.getScore();

        startTile.passedTile(p);

        assertFalse(score==p.getScore());
    }
}
