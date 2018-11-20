package dk.dtu.CDIT_Grp_43_matador.matador;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.DiceCup;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;

import java.util.ArrayList;

public class LogicController {

    private static LogicController INSTANCE = new LogicController();

    private Player[] players;
    private DiceCup diceCup;
    private GameBoard board;
    private LogicController(){}
    private boolean endOfGame = false;

    // Turn base varibels

    private int currPlayerIndex = 0;
    private int currPlayerScore;
    private int currPlayerPosition;
    private int currPlayerRolled;
    private int currPlayerInt;
    private int currPlayerPositionAfterRoll;

    /**
     * Initializes the sigleton class {@code LogicController}
     * @param players The players in the current game
     */

    public void init(Player[] players){
        this.players = players;
        diceCup = DiceCup.getInstance();
        board = GameBoard.getInstance();
    }

    /**
     * A funktion that updates the logic in the game and should be called every logic frame
     */

    public void tick(){
        Player currPlayer = players[currPlayerIndex];

        // Before roll
        currPlayerInt = currPlayerIndex;
        currPlayerPosition = currPlayer.getCurrPos();

        // After roll
        currPlayerRolled = diceCup.roll();
        currPlayer.move(currPlayerRolled);
        currPlayerPositionAfterRoll = currPlayer.getCurrPos();



        if(currPlayerPositionAfterRoll > 23){
            currPlayerPositionAfterRoll = currPlayerPositionAfterRoll%24;
            currPlayer.setCurrPos(currPlayerPositionAfterRoll);
            currPlayer.addMoney(2);
        }

        currPlayerScore = currPlayer.getScore();

        if(++currPlayerIndex >=players.length)
            currPlayerIndex = 0;
    }

    public static LogicController getINSTANCE() {
        return INSTANCE;
    }

    public boolean isEndOfGame() {
        return endOfGame;
    }

    // Getters


    public int getCurrPlayerInt() { return currPlayerInt; }

    public int getCurrPlayerScore() {
        return currPlayerScore;
    }

    public int getCurrPlayerPosition() {
        return currPlayerPosition;
    }

    public int getCurrPlayerRolled() {
        return currPlayerRolled;
    }

    public int getCurrPlayerPositionAfterRoll() {
        return currPlayerPositionAfterRoll;
    }
}
