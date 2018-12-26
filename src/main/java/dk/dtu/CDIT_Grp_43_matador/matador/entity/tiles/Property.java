package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import java.util.ArrayList;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;

public class Property extends Tile {
	private static final int NUMOFTILESINSET = 2;
    private Player owner = null;
    private String sisterTag;
    public String type = "Property";

    /**
     * Class for the property tiles that a player can own, and that others can pay rent to stay on.
     * There are also a sistertile to each property which will double the rent if a player owns both.
     * @param tilename parsed to superclass constructor {@code Tile}.
     * @param tileinfo parsed to superclass constructor {@code Tile}.
     */
    public Property (String tilename, String tileinfo, int tileIndex) {
        super(tilename, tileinfo, tileIndex);
        this.buyable = true;
        String[] tileInfoTags = tileinfo.split(";");
        for (String string : tileInfoTags) {
        	String[] tagInfo = string.split(":");
			switch (tagInfo[0].toLowerCase()) {
			case "sister":
				sisterTag = tagInfo[1];
				break;

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

    /**
     * Used when a player lands on a tile, and if the decides whether the player needs to pay rent, or buy the property.
     * @param p The current player.
     * @return True if everything goes well, and false if the player is out of money ergo lost.
     */
    @Override
    public boolean landOnTile(Player p) {
    	//System.out.println("In land on tile");
    	//System.out.println(this.tileIndex);
        if (owner ==  null && buyable) {
        	super.landOnTile(p);
        	infExch.addToCurrentTurnText(p + " bought the tile for " + tileValue);
            return buyTile(p);
        }
        if (owner != p) {
        	System.out.println("Payed");
        	super.landOnTile(p);
        	infExch.addToCurrentTurnText(p + " landed on a tile owned by " + owner + " and payed them " + (tileSetOwned() ? 2 * tileValue : tileValue));
            return p.payMoney(owner, tileSetOwned() ? 2 * tileValue : tileValue);
        }
        return super.landOnTile(p);
    }

    @Override
    public boolean isOwned(){
        return owner != null;
    }
    
    private boolean tileSetOwned() {
    	ArrayList<Tile> playerOwnedTileSet = owner.getOwnedTiles();
    	int tilesInSetOwned = 0;
    	for (Tile tile : playerOwnedTileSet) {
			if ((tile.getOwner() == this.owner) && (this.sisterTag.equalsIgnoreCase(tile.getSisterTag())))
				tilesInSetOwned++;
		}
    	return NUMOFTILESINSET == tilesInSetOwned;
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

    public Player getOwner() {
        return owner;
    }

    public boolean isBuyalbe () {
        return buyable;
    }
    
    @Override
    public String getSisterTag() {
		return sisterTag;
	}
}
