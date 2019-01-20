package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import java.util.ArrayList;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles.OwnableProperties.Property;

public abstract class Ownable extends Tile {
    protected Player owner = null;
    protected boolean ownedLastTurn = false;
    protected int lastPrice = 0;
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
            switch (tagInfo[0]) {
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
            if ((tile.getOwner() == this.owner) && (sisterTag.equalsIgnoreCase(tile.getSisterTag())))
                tilesInSetOwned++;
        }
        return tilesInSetOwned;
    }

    public boolean tileSetowned(){
        return tilesInSetOwned() == tilesInSet;
    }

    public int getTotalTileValue() {
        int totalValue = tileValue / 2;
        if (this instanceof Property)
            totalValue += ((Property) this).getHouseLevel() * ((Property) this).getHousePrice() / 2;
        return totalValue;
    }

    /**
     * Boolean keeping track of what tile the player just passed. Used for tracking if the player crossed start.
     * @param p The current player.
     * @return Returns true if the method goes as planned, meaning that the player hasn't lost.
     */
    @Override
    public boolean passedTile(Player p) { return true; }

    public boolean isPawned() { return pawned; }

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

    public int getTilesInSet() {
        return tilesInSet;
    }

    @Override
    public String getSisterTag() {
        return sisterTag;
    }

    @Override
    public String printLandOn(Player p) {
        String result = p + " landede på " + tileName;
        if (owner == null) {
            result += " men der var ingen der købte " + tileName;
        } else if (owner == p && !ownedLastTurn) {
            ownedLastTurn = true;
            result += " og købte " + tileName;
        } else if (owner == p) {
            result += " og ejede allerede feltet, så der skete intet";
        } else if (owner != p && !ownedLastTurn) {
            ownedLastTurn = true;
            result += " og feltet røt på auktion og blev købt af " + owner;
        } else if (owner != p) {
            result += " som er ejet af " + owner + " og betalte dem " + lastPrice;
        }


        return result;
    }

    @Override
    public String printPassed(Player p) {
        String result = "";
        return result;
    }
}
