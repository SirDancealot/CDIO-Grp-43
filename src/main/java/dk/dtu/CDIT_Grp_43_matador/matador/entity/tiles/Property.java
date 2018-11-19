package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;

public class Property extends Tile {
    private Player owner = null;
    private Tile sisterTile;

    /**
     *
     * @param tilename
     * @param tileinfo
     */
    public Property (String tilename, String tileinfo) {
        super(tilename, tileinfo);
        this.owner = owner;
        this.buyable = true;
        int rent = tileValue;
    }

    /**
     * This is a function to buy this instance of a tile.
     * @param p the player thats buying the tile.
     * @return wether this tile was succesfully bought.
     */
    public boolean buyTile (Player p) {
        if (p.withdrawMoney(tileValue)) {
            this.owner = p;
            return true;
        }
        return false;
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public boolean landOnTile(Player p) {
        if (owner ==  null) {
            return buyTile(p);
        }
        if (owner != p) {
            return p.payMoney(owner, tileValue);
        }
        return true;
    }
    public boolean isBuyalbe () {
        return buyable;
    }
    @Override
    public boolean passedTile(Player p) {
        return true;
    }
}
