package dk.dtu.CDIT_Grp_43_matador.jUnitTest;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Account;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testAfScore {


    @Test
    public void scoreAdd(){

        Player p = new Player("testPlayer", 20);
        int money = p.getScore();
        p.addMoney(30);
        assertTrue(money != p.getScore());
        System.out.println("----Test af Add----");
        System.out.println("Penge før:" + money + " og penge efter:"+ p.getScore());

    }

    @Test
    public void scoreSubtract(){

        Player p = new Player("testPlayer", 20);
        int money = p.getScore();
        p.withDrawMoney(10);
        assertFalse(money == p.getScore());
        System.out.println("----Test af Subtract----");
        System.out.println("Penge før:" + money + " og penge efter:" + p.getScore());

    }

    @Test
    public void scorePay(){

        Player p = new Player("testPlayer", 20);
        Player p1 = new Player("testPlayer1", 20);
        int money = p.getScore();
        int money1 = p1.getScore();

        p.payMoney(p1,2);

        money = p.getScore();
        money1 = p1.getScore();
        assertTrue(money < money1);
        System.out.println("----Test af Pay----");
        System.out.println("Penge før for spiller 1:" + money + " og penge efter:" + p.getScore());
        System.out.println("Penge før for spiller 2:" + money1 + " og penge efter:" + p1.getScore());

    }
}
