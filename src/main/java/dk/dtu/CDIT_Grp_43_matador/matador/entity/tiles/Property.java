package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;

public class Property extends Tile {
    private Player owner = null;
    private Tile sisterTile;
    public String type = "Property";

    /**
     * Class for the property tiles that a player can own, and that others can pay rent to stay on.
     * There are also a sistertile to each property which will double the rent if a player owns both.
     * @param tilename parsed to superclass constructor {@code Tile}.
     * @param tileinfo parsed to superclass constructor {@code Tile}.
     */
    public Property (String tilename, String tileinfo, int tileIndex) {
        super(tilename, tileinfo);
        this.owner = owner;
        this.buyable = true;
        int rent = tileValue;
    }

    /**
     * This is a function to buy this instance of a tile.
     * @param p the player that's buying the tile.
     * @return True if the purchase was a success.
     */
    public boolean buyTile (Player p) {
        if (p.withDrawMoney(tileValue)) {
            this.owner = p;
            return true;
        }
        return false;
    }

    /**
     * Used when a player lands on a tile, and if the decides whether the player needs to pay rent, or buy the property.
     * @param p The current player.
     * @return True if everything goes well, and false if the player is out of money ergo lost.
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

    /**
     * Boolean keeping track of what tile the player just passed. Used for tracking if the player crossed start.
     * @param p The current player.
     * @return Returns true if the method goes as planned, meaning that the player hasn't lost.
     */
    @Override
    public boolean passedTile(Player p) {
        return true;
    }

    public boolean isBuyalbe () {
        return buyable;
    }

}
