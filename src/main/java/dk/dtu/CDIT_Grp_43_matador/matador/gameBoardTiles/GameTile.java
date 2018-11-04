package dk.dtu.CDIT_Grp_43_matador.matador.gameBoardTiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.GameBoard;

public class GameTile {

    private int tileValue;
    private String tileName;

    public GameTile(String tileName, int tileValue){
        this.tileValue = tileValue;
        this.tileName = tileName;
    }

    // Getter

    public int getTileValue() {
        return tileValue;
    }

    public String getTileName() {
        return tileName;
    }

}
