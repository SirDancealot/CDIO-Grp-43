package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;

public class Jail extends Tile {
    private int outOfJailPrice = 1;
    public Jail (String tilename, String tileinfo) {
        super (tilename, tileinfo);
    }

    @Override
    public boolean passedTile(Player p) {
        if (p.isInJail())
            return p.withdrawMoney(outOfJailPrice);
        return true;
    }
}
