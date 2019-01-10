package dk.dtu.CDIT_Grp_43_matador.matador;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.util.InformationExchanger;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.DiceCup;
import dk.dtu.CDIT_Grp_43_matador.matador.wraperClasses.GameBoard;

public class LogicController {

    private static LogicController INSTANCE = new LogicController();
    private final int TURNLIMIT = 100;
    
    private Player[] players;
    private DiceCup diceCup;
    private GameBoard board;
    private InformationExchanger infExch = InformationExchanger.getInstance();
    private boolean endOfGame = false;
    private int turns = 0;
    private String turnInfo;
    private GameController game = GameController.getInstance();

    // Turn base variables

    private int currPlayerIndex = 0;

    private LogicController(){}

    /**
     * Initializes the sigleton class {@code LogicController}
     * @param players The players in the current game
     */

    public void init(Player[] players){
        this.players = players;
        Player.setPlayers(players);
        infExch.setPlayers(players);
        diceCup = DiceCup.getInstance();
        board = GameBoard.getInstance();
        endOfGame = false;
    }

    /**
     * A function that updates the logic in the game and should be called every logic frame
     */

    public void tick(){
        turnInfo = "";

        // Reset current turn text
        infExch.setCurrentTurnText("");

        Player currPlayer = players[currPlayerIndex];
        infExch.setCurrPlayer(currPlayer);
        infExch.setCurrPlayerIndex(currPlayerIndex);

        // After roll
        int roll = diceCup.roll();
        infExch.setCurrPlayerRolled(roll);
        infExch.setCurrPlayerOldPos(currPlayer.getCurrPos());
        for (int i = 0; i < roll; i++) {
			if (currPlayer.isFirstTurn())
				currPlayer.setFirstTurn(false);
			else
				board.passedTile(currPlayer, (currPlayer.getCurrPos() + i) % board.getBoardSize());
		}
        
        if(!currPlayer.move(roll)){
        	infExch.setCurrPlayerNewPos(currPlayer.getCurrPos());
            endOfGame = true;
            infExch.setCurrPlayerScore(currPlayer.getScore());
            infExch.setTileOwned(currPlayer == board.getTileOwner(currPlayer.getCurrPos()));
            return;
        }
        infExch.setCurrPlayerNewPos(currPlayer.getCurrPos());
        infExch.setTileOwned(currPlayer == board.getTileOwner(currPlayer.getCurrPos()));
        infExch.setCurrPlayerScore(currPlayer.getScore());


        if(++currPlayerIndex >= players.length){
            currPlayerIndex = 0;
            turns++;
        }
        if (turns > TURNLIMIT) {
        	endOfGame = true;
        	infExch.addToCurrentTurnText("\nThe game will now be terminated due to too many turns having taken place without anyone loosing");
        }
        game.postTurnInfo(turnInfo);
    }

    public static LogicController getINSTANCE() {
        return INSTANCE;
    }

    public boolean isEndOfGame() {
        return endOfGame;
    }
    
    
}
