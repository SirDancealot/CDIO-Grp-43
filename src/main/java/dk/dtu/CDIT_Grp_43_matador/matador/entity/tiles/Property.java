package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;

public class Property extends Tile {
    private Player ejer = null;

    public Property (String tilename, String tileinfo) {
        super(tilename, tileinfo);
        this.ejer = ejer;
        int a = tileValue;
    }
}
