package dk.dtu.CDIT_Grp_43_matador.matador;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.DiceCup;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;

public class LogicController {

    private static LogicController INSTANCE = new LogicController();

    private Player[] players;
    private int currPlayerIndex = 0;
    private DiceCup diceCup;
    private GameBoard board;
    private LogicController(){}
    private boolean endOfGame = false;

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

        int roll = diceCup.roll();

        int oldPos = currPlayer.getCurrPos();

        currPlayer.move(roll);

        for (int pos = oldPos; pos < oldPos+roll; pos++){
            board.passedTile(currPlayer, pos%board.getBoardSize());
        }

        if(!board.landOnTile(currPlayer)){
            endOfGame = true;
            return ;
        }

        if(++currPlayerIndex >=players.length)
            currPlayerIndex = 0;
    }

    public static LogicController getINSTANCE() {
        return INSTANCE;
    }

    public boolean isEndOfGame() {
        return endOfGame;
    }
}
