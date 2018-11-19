package dk.dtu.CDIT_Grp_43_matador.matador;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.DiceCup;
import org.junit.jupiter.api.RepeatedTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MatadorMainBalanceTest {
	private GameController game = GameController.getInstance();
    private Player[] players;



    public void setup() throws IOException {
    	game.resetGame();
    	game.init(new String[] {"0" , "2"});
        DiceCup dc = DiceCup.getInstance();
        dc.changeCustomDice(new int[] {1}, new int[] {2});
        players = game.getPlayers();

    }

    


    @RepeatedTest(1000)
    public void winningTest() throws IOException {
        setup();

        while(game.isPlaying()){
        	game.tick();

           assertTrue(players[game.getCurrPlayer()].getScore()>=0);
            if(game.getTurn()>100)
                break;
        }
    }

}

