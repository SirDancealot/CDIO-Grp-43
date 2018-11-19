package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;

public class goToJail extends Tile {

    public goToJail (String tilename, String tileinfo) {
        super (tilename, tileinfo);
    }
    @Override
    public boolean landOnTile(Player p) {
        p.setInJail(true);
        return true;
    }


}
