package dk.dtu.CDIT_Grp_43_matador.matador;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.DiceCup;
import org.junit.jupiter.api.RepeatedTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MatadorMainBalanceTest {

    private Player[] players;



    public void setup() throws IOException {
        Matador.resetGame();
        Matador.init(new String[] {"0" , "2"});
        DiceCup dc = DiceCup.getInstance();
        dc.changeCustomDice(new int[] {1}, new int[] {2});
        players = Matador.getPlayers();

    }

    


    @RepeatedTest(1000)
    public void winningTest() throws IOException {


        setup();

        while(Matador.isPlaying()){
            Matador.tick();

           assertTrue(players[Matador.getCurrPlayer()].getScore()>=0);
            if(Matador.getTurn()>100)
                break;

        }
    }

}

