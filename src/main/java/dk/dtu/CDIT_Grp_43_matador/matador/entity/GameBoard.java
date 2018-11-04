package dk.dtu.CDIT_Grp_43_matador.matador.entity;

import dk.dtu.CDIT_Grp_43_matador.matador.gameBoardTiles.*;
import java.util.HashMap;

public class GameBoard {
    private String[] gameTilesNames = {"(Man kan ikke slå 1 med to terninger)","Tower","Crater ","Palace gates", "Cold Desert", "Walled city", "Monastery",
            "Black cave", "Huts in the mountain", "The Werewall (werewolf-wall)", "The pit", "Goldmine"};
    private int[] gameTilesValue = {0,250,-100,100, -20, 180, 0, -70, 60, -80, -50, 650};

    private GameTile[] gameTiles = new GameTile[gameTilesNames.length];


    public GameBoard(){
        createBoard();
    }

    private void createBoard() {

        for(int i = 0; i < gameTilesNames.length; i++){
            gameTiles[i] = new GameTile(gameTilesNames[i], gameTilesValue[i]);
        }
    }

    // Getters

    public GameTile[] getGameTiles() {
        return gameTiles;
    }

}
