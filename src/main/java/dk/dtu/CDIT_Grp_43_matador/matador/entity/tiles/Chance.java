package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;

public class Chance extends Tile {

    public Chance (String tilename, String tileinfo) {
        super (tilename, tileinfo);

    }
    public boolean landOnTile(Player p) {
        // insert drawChanceCard method
        return true;

    }

}
