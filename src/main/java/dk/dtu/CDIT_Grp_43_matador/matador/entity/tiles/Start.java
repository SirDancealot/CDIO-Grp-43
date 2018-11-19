package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;

public class Start extends Tile {
    private int overStartBonus = 2;

    public Start(String tilename, String tileinfo) {
        super (tilename, tileinfo);
    }

    @Override
    public boolean passedTile(Player p) {
        return p.addMoney(overStartBonus);
    }

}