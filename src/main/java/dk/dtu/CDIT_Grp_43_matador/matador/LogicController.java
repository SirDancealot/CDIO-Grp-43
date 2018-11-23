package dk.dtu.CDIT_Grp_43_matador.matador;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.util.InformationExchanger;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.DiceCup;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;

public class LogicController {

    private static LogicController INSTANCE = new LogicController();

    private Player[] players;
    private DiceCup diceCup;
    private GameBoard board;
    private InformationExchanger infExch = InformationExchanger.getInstance();
    private LogicController(){}
    private boolean endOfGame = false;

    // Turn base variables

    private int currPlayerIndex = 0;

    /**
     * Initializes the sigleton class {@code LogicController}
     * @param players The players in the current game
     */

    public void init(Player[] players){
        this.players = players;
        infExch.setPlayers(players);
        diceCup = DiceCup.getInstance();
        board = GameBoard.getInstance();
    }

    /**
     * A funktion that updates the logic in the game and should be called every logic frame
     */

    public void tick(){
        Player currPlayer = players[currPlayerIndex];
        infExch.setCurrPlayer(currPlayer);
        infExch.setCurrPlayerIndex(currPlayerIndex);

        // After roll
        int roll = diceCup.roll();
        infExch.setCurrPlayerRolled(roll);
        infExch.setCurrPlayerOldPos(currPlayer.getCurrPos());
        currPlayer.move(roll);
        infExch.setCurrPlayerNewPos(currPlayer.getCurrPos());

        if(!board.landOnTile(currPlayer)){
            endOfGame = true;
            infExch.setCurrPlayerScore(currPlayer.getScore());
            return ;
        }
        infExch.setTileOwned(currPlayer == board.getTileOwner(currPlayer.getCurrPos()));
        infExch.setCurrPlayerScore(currPlayer.getScore());

        if(++currPlayerIndex >= players.length)
            currPlayerIndex = 0;
    }

    public static LogicController getINSTANCE() {
        return INSTANCE;
    }

    public boolean isEndOfGame() {
        return endOfGame;
    }
}
