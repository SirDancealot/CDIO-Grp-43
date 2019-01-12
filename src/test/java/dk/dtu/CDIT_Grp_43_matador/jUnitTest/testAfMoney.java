package dk.dtu.CDIT_Grp_43_matador.jUnitTest;


import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Account;
import org.junit.jupiter.api.Test;

public class testAfMoney{




    @Test
    public void moneyAdd(){

        Player p = new Player("testPlayer", 10000);
        int money = p.getScore();

        System.out.println(money);
    }
    @Test
    public void moneySubtract(){



    }

}
