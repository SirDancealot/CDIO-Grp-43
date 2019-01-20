package dk.dtu.CDIT_Grp_43_matador.jUnitTest;

import dk.dtu.CDIT_Grp_43_matador.matador.Logic;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.ChanceCardDeck;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class testAfWinRate {

    int games = 1000;
    double high = (games/4)*1.10;
    double low = (games/4)*0.90;

    private static Player[] players;
    private static GameBoard bord = GameBoard.getInstance();
    private static ChanceCardDeck deck = ChanceCardDeck.getInstance();
    private static Logic logic = Logic.getInstance();


    public void rungame() throws IOException {
        init();

        while(!logic.isEndOfGame()){
            logic.tick();
        }
    }
    @Test
    public void testSpil() throws IOException {
        int[] wins = new int[4];
        for (int i = 0; i < games; i++) {
            rungame();
        }
        assertTrue(low < wins[0] && wins[0] < high);
        System.out.println("Player1 wins:" + wins[0] + "times.\nPlayer2 wins:" + wins[1] + " times.\nPlayer3 wins:" + wins[2] + " times.\nPlayer4 wins:" + wins[3] + "times.");
    }
    public void init() throws IOException {

        //The Custom Stream Tokenizer is initialized

        int numPlayers = 4;
        String[] names = new String[]{"Player1","Player2","Player3","Player4"};

        bord.initBoard();

        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(names[i], 20);
        }
        logic.init(players);
    }
}
