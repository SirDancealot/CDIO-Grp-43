package dk.dtu.CDIT_Grp_43_matador.matador.entity;

public abstract class Tile {

    protected int tileValue;
    protected boolean buyable = false;
    protected boolean extraTurn = false;
    protected String tileName;
    protected String tileMessage;
    protected String type;

    /**
     * Super constructor for all tile classes, should be called in all sub classes.
     *
     * @param tileName       the name on the current tile gathered from Tiles.txt file.
     * @param tileInfoString Info regarding the different properties for each tile, such as rent, and type of tile.
     */
    public Tile(String tileName, String tileInfoString) {
        String[] nameInfo = tileName.split(";");
        this.tileName = nameInfo[0];
        this.tileMessage = nameInfo[1];

        String[] tileInfo = tileInfoString.split(";");
        for (String string : tileInfo) {
            String[] split = string.split(":");
            switch (split[0]) {
                case "Score":
                    tileValue = Integer.valueOf(split[1]);
                    break;
                case "ExtraTurn":
                    extraTurn = true;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Boolean keeping track of what tile the player just passed. Used for tracking if the player crossed start.
     *
     * @param p The current player.
     * @return Returns true if the method goes as planned, meaning that the player hasn't lost.
     */
    public boolean passedTile(Player p) {
        return true;
    }

    /**
     * Method used when the player lands on a tile. Gets elaborated on in the tile sub classes.
     *
     * @param p The current player.
     * @return Returns true if the method goes as planned, meaning that the player hasn't lost.
     */
    public boolean landOnTile(Player p) {
        return true;
    }

    // Getters

    public int getTileValue() {
        return tileValue;
    }

    public String getTileName() {
        return tileName;
    }

    public String getTileMessage() {
        return tileMessage;
    }

    public boolean givesExtraTurn() {
        return extraTurn;
    }
}

