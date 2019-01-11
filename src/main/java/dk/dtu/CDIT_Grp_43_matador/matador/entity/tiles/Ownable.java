package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import java.util.ArrayList;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class Ownable extends Tile {
    protected Player owner = null;
    private String sisterTag;
    protected int tilesInSet;
    protected boolean pawned = false;



    /**
     * Class for the ownable tiles that a player can own, and that others can pay rent to stay on.
     * There are also sistertiles to each ownable tile which will double the rent if a player owns both.
     * @param tilename parsed to superclass constructor {@code Tile}.
     * @param tileinfo parsed to superclass constructor {@code Tile}.
     */
    public Ownable (String tilename, String tileinfo, int tileIndex) {
        super(tilename, tileinfo, tileIndex);
        this.buyable = true;
        String[] tileInfoTags = tileinfo.split(";");
        for (String string : tileInfoTags) {
            String[] tagInfo = string.split(":");
            switch (tagInfo[0].toLowerCase()) {
                case "sister":
                    sisterTag = tagInfo[1];
                    break;
                case "setSize":
                    tilesInSet = Integer.valueOf(tagInfo[1]);


                default:
                    break;
            }
        }
    }

    /**
     * This is a function to buy this instance of a tile.
     * @param p the player that's buying the tile.
     * @return True if the purchase was a success.
     */
    public boolean buyTile (Player p) {
        if (p.withDrawMoney(tileValue)) {
            this.owner = p;
            p.addOwnedTile(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOwned(){
        return owner != null;
    }

    protected int tilesInSetOwned() {
        ArrayList<Tile> playerOwnedTileSet = owner.getOwnedTiles();
        int tilesInSetOwned = 0;
        for (Tile tile : playerOwnedTileSet) {
            if ((tile.getOwner() == this.owner) && (this.sisterTag.equalsIgnoreCase(tile.getSisterTag())))
                tilesInSetOwned++;
        }
        return tilesInSetOwned;
    }

    protected boolean tileSetowned(){
        return tilesInSetOwned() == tilesInSet;
    }

    /**
     * Boolean keeping track of what tile the player just passed. Used for tracking if the player crossed start.
     * @param p The current player.
     * @return Returns true if the method goes as planned, meaning that the player hasn't lost.
     */
    @Override
    public boolean passedTile(Player p) { return true; }

    public boolean isPawned() {return pawned; }

    public void setPawned(boolean pawned) {
        this.pawned = pawned;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isBuyable () {
        return buyable;
    }

    @Override
    public String getSisterTag() {
        return sisterTag;
    }
}
