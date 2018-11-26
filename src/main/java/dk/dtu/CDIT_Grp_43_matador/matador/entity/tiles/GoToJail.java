package dk.dtu.CDIT_Grp_43_matador.matador.entity.tiles;

import dk.dtu.CDIT_Grp_43_matador.matador.entity.Player;
import dk.dtu.CDIT_Grp_43_matador.matador.entity.Tile;

public class GoToJail extends Tile {
    public String type = "GoToJail";

    /**
     * Used to control what happens to the player when landing on the GoToJail tile.
     * @param tilename parsed to superclass constructor {@code Tile}.
     * @param tileinfo parsed to superclass constructor {@code Tile}.
     */
    public GoToJail(String tilename, String tileinfo, int tileIndex) {
        super (tilename, tileinfo, tileIndex);
    }

    /**
     * Sets the players inJail boolean to true, and moves their position to the Jail tile.
     * @param p The current player.
     * @return Returns true if everything goes right.
     */
    @Override
    public boolean landOnTile(Player p) {
        p.setInJail(true);
        p.moveTo("jail");
        return true;
    }


}
