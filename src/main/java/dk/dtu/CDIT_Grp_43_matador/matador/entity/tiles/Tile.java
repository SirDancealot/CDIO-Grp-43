package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.util.InformationExchanger;

public abstract class Tile {
    protected int tileIndex;
    protected int tileValue;
    protected boolean buyable = false;
    protected String tileName;
    protected String tileMessage;
    protected String type;
    protected InformationExchanger infExch = InformationExchanger.getInstance();

    /**
     * Super constructor for all tile classes, should be called in all sub classes.
     * @param tileName the name on the current tile gathered from Tiles.txt file.
     * @param tileInfoString Info regarding the different properties for each tile, such as rent, and type of tile.
     */
    public Tile(String tileName, String tileInfoString, int tileIndex) {
        String[] nameInfo = tileName.split(";");
        this.tileIndex = tileIndex;
        String[] tileInfo = tileInfoString.split(";");
        for (String string : tileInfo) {
            String[] split = string.split(":");
            switch (split[0]) {
                case "Tilevalue":
                    tileValue = Integer.valueOf(split[1]);
                    break;
                case "name":
                    this.tileName = split[1];
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Boolean keeping track of what tile the player just passed. Used for tracking if the player crossed start.
     * @param p The current player.
     * @return Returns true if the method goes as planned, meaning that the player hasn't lost.
     */
    public boolean passedTile(Player p) {
        return true;
    }

    public Player getOwner() {
        return null;
    }

    public  boolean isOwned() {
        return false;
    }

    /**
     * Method used when the player lands on a tile. Gets elaborated on in the tile sub classes.
     * @param p The current player.
     * @return Returns true if the method goes as planned, meaning that the player hasn't lost.
     */
    public boolean landOnTile(Player p) {
    	System.out.println("Landed on tile: " + this);
        return true;
    }

    // Getters

    public int getTileValue() {
        return tileValue;
    }

    public int getTileIndex() {
        return tileIndex;
    }

    public String getTileName() {
        return tileName;
    }

    public String getTileMessage() {
        return tileMessage;
    }
    
    @Override
    public String toString() {
    	return this.tileName;
    }

	public String getSisterTag() {
		return null;
	}
}

