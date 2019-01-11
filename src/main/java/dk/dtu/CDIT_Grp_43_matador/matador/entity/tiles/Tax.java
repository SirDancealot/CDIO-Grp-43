package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class Tax extends Tile {
    public Tax(String tileName, String tileInfo, int tileIndex) {
        super(tileName, tileInfo, tileIndex);
    }

    @Override
    public boolean landOnTile(Player p) {

        return super.landOnTile(p);

    }
}
