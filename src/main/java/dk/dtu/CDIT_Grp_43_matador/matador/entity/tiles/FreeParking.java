package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;

public class FreeParking extends Tile {

    public FreeParking(String tilename, String tileinfo) {
        super (tilename, tileinfo);
    }

    public boolean passedTile (Player p) {
        return true;
    }
}
